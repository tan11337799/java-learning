package com.twhupup.servlet;

import com.twhupup.config.SpringConfig;
import com.twhupup.entity.Fruit;
import com.twhupup.service.FruitService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Demo03 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        FruitService fruitService = context.getBean("fruitService", FruitService.class);
        //tomcat8,对post方式设置编码，get方式不需要设置
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("fname");
        int price = Integer.parseInt(req.getParameter("price"));
        int number = Integer.parseInt(req.getParameter("fcount"));
        String des = req.getParameter("remark");
        int flag = fruitService.addFruit(new Fruit(name, price, number,des));
        System.out.println(flag==1?"添加成功！":"添加失败！");;
    }
}
