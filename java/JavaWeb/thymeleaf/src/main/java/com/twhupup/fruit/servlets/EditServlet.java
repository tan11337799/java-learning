package com.twhupup.fruit.servlets;

import com.twhupup.config.SpringConfig;
import com.twhupup.fruit.dao.FruitDAO;
import com.twhupup.fruit.dao.FruitDAOImpl;
import com.twhupup.fruit.pojo.Fruit;
import com.twhupup.fruit.service.FruitService;
import com.twhupup.myssm.myspringmvc.ViewBaseServlet;
import com.twhupup.myssm.util.StringUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit.do")
public class EditServlet extends ViewBaseServlet {

    private FruitDAO fruitDAO = new FruitDAOImpl();

    @Override
    public void doGet(HttpServletRequest request , HttpServletResponse response)throws IOException, ServletException {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        FruitService fruitService = context.getBean("fruitService", FruitService.class);
        String fidStr = request.getParameter("fid");
        if(StringUtil.isNotEmpty(fidStr)){
            int fid = Integer.parseInt(fidStr);
            Fruit fruit = fruitService.getFruitByFid(fid);
            request.setAttribute("fruit",fruit);
            super.processTemplate("edit",request,response);
        }
    }
}
