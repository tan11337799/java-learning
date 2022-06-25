package com.twhupup.myssm.myspringmvc;

import com.twhupup.config.SpringConfig;
import com.twhupup.myssm.util.StringUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @Project: fruit-management-app
 * @Package: com.twhupup.servlets
 * @Date: 2022/6/23 23:20
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */

@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        String servletPath = request.getServletPath();
        servletPath = servletPath.substring(1,servletPath.length()-3);
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        Object controllerBean = context.getBean(servletPath);
        String operate = request.getParameter("operate");
        if (StringUtil.isEmpty(operate)) {
            operate = "index";
        }
        try {
            Method[] declaredMethods = controllerBean.getClass().getDeclaredMethods();
            for(Method method: declaredMethods){
                if(operate.equals(method.getName())){
                    //1.统一获取请求参数
                    //获取当前方法的形参列表
                    Parameter[] parameters = method.getParameters();
                    //parameterValues用于存放需要传入的参数对象
                    Object[] parameterValues = new Object[parameters.length];
                    for (int i = 0; i < parameters.length; i++) {
                        //获取当前方法的参数名称
                        Parameter parameter = parameters[i];
                        String parameterName = parameter.getName();
                        if("request".equals(parameterName)){
                            parameterValues[i] = request;
                        }else if("response".equals(parameterName)){
                            parameterValues[i] = response;
                        }else if("session".equals(parameterName)){
                            parameterValues[i] = request.getSession();
                        }else {
                            String parameterValue = request.getParameter(parameterName);
                            String typeName = parameter.getType().getName();
                            Object parameterObj = parameterValue ;
                            if(parameterObj!=null) {
                                if ("java.lang.Integer".equals(typeName)) {
                                    parameterObj = Integer.parseInt(parameterValue);
                                }
                            }
                            parameterValues[i] = parameterObj ;
                        }
                    }

                    //2.controller方法调用
                    method.setAccessible(true);
                    Object returnObj = method.invoke(controllerBean,parameterValues);

                    //3.视图处理
                    String methodReturnStr = (String)returnObj;
                    if(methodReturnStr.startsWith("redirect")){
                        String redirectStr = methodReturnStr.substring("redirect:".length());
                        response.sendRedirect(redirectStr);
                    }else{
                        super.processTemplate(methodReturnStr,request,response);
                    }

                }
            }
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
