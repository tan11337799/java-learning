package com.twhupup.servlets;

import com.twhupup.config.SpringConfig;
import com.twhupup.dao.FruitDAO;
import com.twhupup.dao.FruitDAOImpl;
import com.twhupup.service.FruitService;
import com.twhupup.myssm.myspringmvc.ViewBaseServlet;
import com.twhupup.myssm.util.StringUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/del.do")
public class DelServlet extends ViewBaseServlet {
    private FruitDAO fruitDAO = new FruitDAOImpl();
    @Override
    public void doGet(HttpServletRequest request , HttpServletResponse response)throws IOException, ServletException {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        FruitService fruitService = context.getBean("fruitService", FruitService.class);
        String fidStr = request.getParameter("fid");
        if(StringUtil.isNotEmpty(fidStr)){
            int fid = Integer.parseInt(fidStr);
            fruitService.delFruit(fid);
            //super.processTemplate("index",request,response);
            response.sendRedirect("index");
        }
    }
}
