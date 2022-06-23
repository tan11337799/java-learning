package com.twhupup.servlets;

import com.twhupup.config.SpringConfig;
import com.twhupup.entity.Fruit;
import com.twhupup.myssm.myspringmvc.ViewBaseServlet;
import com.twhupup.myssm.util.StringUtil;
import com.twhupup.service.FruitService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//Servlet从3.0版本开始支持注解方式的注册
@WebServlet("/index")
public class IndexServlet extends ViewBaseServlet {
    @Override
    public void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException, ServletException {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        FruitService fruitService = context.getBean("fruitService", FruitService.class);
        int pageNum = 1;
        String pageNumString = request.getParameter("pageNum");
        if(StringUtil.isNotEmpty(pageNumString)){
            pageNum = Integer.parseInt(pageNumString);
        }
        request.getSession().setAttribute("pageNum",pageNum);
        List<Fruit> fruitList = fruitService.findAllFruitByPage(pageNum);
        int fruitCount = fruitService.getFruitCount();
        //保存到session作用域
        request.getSession().setAttribute("fruitList",fruitList);
        request.getSession().setAttribute("pageCount",Math.ceil(fruitCount/3.0));
        super.processTemplate("index",request,response);
    }
}
