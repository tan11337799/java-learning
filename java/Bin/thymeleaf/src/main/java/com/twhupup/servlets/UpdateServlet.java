package com.twhupup.servlets;

import com.twhupup.config.SpringConfig;
import com.twhupup.fruit.Fruit;
import com.twhupup.myssm.myspringmvc.ViewBaseServlet;
import com.twhupup.service.FruitService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update.do")
public class UpdateServlet extends ViewBaseServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        FruitService fruitService = context.getBean("fruitService", FruitService.class);
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取参数
        Integer fid = Integer.parseInt(request.getParameter("fid"));
        String fname = request.getParameter("fname");
        int price = Integer.parseInt(request.getParameter("price"));
        Integer fcount = Integer.parseInt(request.getParameter("fcount"));
        String remark = request.getParameter("remark");
        //3.执行更新
        fruitService.updateFruit(new Fruit(fid,fname, price ,fcount ,remark ));
        //4.资源跳转
        //super.processTemplate("index",request,response);
        //request.getRequestDispatcher("index.html").forward(request,response);
        // 此处需要重定向，目的是重新给IndexServlet发请求，重新获取furitList，
        // 然后覆盖到session中，这样index.html页面上显示的session中的数据才是最新的
        response.sendRedirect("index");
    }
}

// java.lang.NumberFormatException: For input string: ""