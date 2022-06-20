package com.twhupup.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//服务器内部转发
@WebServlet("/dispatch")
public class Demo06 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(this.getClass().getName());
        req.getRequestDispatcher("otherUrl").forward(req,resp);
        // resp.sendRedirect("otherUrl");
    }
}
