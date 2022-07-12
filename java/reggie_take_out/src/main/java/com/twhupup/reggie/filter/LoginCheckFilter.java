package com.twhupup.reggie.filter;

import com.alibaba.fastjson.JSON;
import com.twhupup.reggie.common.BaseContext;
import com.twhupup.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Project: reggie_take_out
 * Package: com.twhupup.reggie.filter
 * Date: 2022/7/8 15:34
 * Author: Wenhao Tan
 * Version: 1.0
 * License: (C)2022,MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
@Slf4j
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    //路径匹配器，支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        //1.获取本次请求的URI
        String requestURI = request.getRequestURI();
        log.info("拦截到请求：{}",requestURI);
        //2.判断本次请求是否需要处理
        String[] permittedUrls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/common/**",
                "/user/sendMsg",
                "/user/login"
        };
        boolean check = check(permittedUrls, requestURI);
        //3、如果不需要处理，则直接放行
        if(check){
            log.info("本次请求{}不需要处理",requestURI);
            filterChain.doFilter(request,response);
            return;
        }
        //4-1.判断后端登陆状态，如果已登陆则放行
        if(request.getSession().getAttribute("employee")!=null){
            log.info("用户已登陆，用户id为:{}",request.getSession().getAttribute("employee"));
            //登陆状态下，将当前用户的id设置到当前线程的ThreadLocal中
            Long empId = (Long)request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);
            filterChain.doFilter(request,response);
            return;
        }
        //4-2、判断移动端登陆状态，如果已登陆则放行
        if(request.getSession().getAttribute("user")!=null){
            log.info("用户已登陆，用户id为:{}",request.getSession().getAttribute("user"));
            //登陆状态下，将当前用户的id设置到当前线程的ThreadLocal中
            Long userId = (Long)request.getSession().getAttribute("user");
            BaseContext.setCurrentId(userId);
            filterChain.doFilter(request,response);
            return;
        }


        //5.如果未登录，则返回未登录状态（通过输出流方式向客户端响应数据）
        log.info("用户未登录，已跳转至登录页！");
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
    }

    /**
     * 路径匹配，检查当前请求是否需要放行
     */
    public boolean check(String[] urls, String requestURI){
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if(match){
                return true;
            }
        }
        return false;
    }
}
