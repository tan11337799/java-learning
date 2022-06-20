package com.twhupup.fruit.servlets;

import com.twhupup.config.SpringConfig;
import com.twhupup.fruit.pojo.Fruit;
import com.twhupup.fruit.service.FruitService;
import com.twhupup.myssm.myspringmvc.ViewBaseServlet;
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
        List<Fruit> fruitList = fruitService.findAllFruit();
        //保存到session作用域
        request.getSession().setAttribute("fruitList",fruitList);
        //此处的视图名称是 index
        //那么thymeleaf会将这个 逻辑视图名称 对应到 物理视图 名称上去
        //逻辑视图名称 ：   index
        //物理视图名称 ：   view-prefix + 逻辑视图名称 + view-suffix
        //所以真实的视图名称是：      /       index       .html
        super.processTemplate("index",request,response);
    }
}
