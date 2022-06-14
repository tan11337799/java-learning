package com.twhupup.servlet;

import com.twhupup.config.SpringConfig;
import com.twhupup.entity.Fruit;
import com.twhupup.service.FruitService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        FruitService fruitService = context.getBean("fruitService", FruitService.class);
        Fruit fruit = new Fruit();
        fruit.setName(req.getParameter("fname"));
        fruit.setPrice(Integer.parseInt(req.getParameter("price")));
        fruit.setNumber(Integer.parseInt(req.getParameter("fcount")));
        // String remark = req.getParameter("remark");
        fruitService.addFruit(fruit);
    }
}
