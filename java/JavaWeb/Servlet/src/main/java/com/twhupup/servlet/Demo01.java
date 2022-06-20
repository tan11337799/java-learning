package com.twhupup.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

public class Demo01 extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("init");
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("service");
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
