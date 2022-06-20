package com.twhupup.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Demo02 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //如果获取不到，则创建一个session
        HttpSession session = req.getSession();
        System.out.println("session id="+session.getId());
    }

    // @Override
    // protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //     System.out.println("get");
    // }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("post");
    }
}
