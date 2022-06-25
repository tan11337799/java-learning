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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

//Servlet从3.0版本开始支持注解方式的注册
@WebServlet("/index")
public class IndexServlet extends ViewBaseServlet {
    @Override
    public void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException, ServletException {
        //设置编码
        request.setCharacterEncoding("UTF-8");
        //获取service容器
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        FruitService fruitService = context.getBean("fruitService", FruitService.class);
        //设置变量初始值
        String keyword = "";
        int pageNum = 1;
        //创建session
        HttpSession session = request.getSession();
        String operation = request.getParameter("oper");
        if(StringUtil.isNotEmpty(operation) && operation.equals("search")){
            pageNum = 1;
            keyword = request.getParameter("keyword");
            session.setAttribute("keyword",keyword);
        }else{
            String pageNumString = request.getParameter("pageNum");
            if(StringUtil.isNotEmpty(pageNumString)){
                pageNum = Integer.parseInt(pageNumString);
            }
            Object keywordObj = session.getAttribute("keyword");
            if(keywordObj!=null){
                keyword = (String)keywordObj;
            }
        }
        //返回指定页数的水果列表
        List<Fruit> fruitList = fruitService.findAllFruit(keyword,pageNum);
        //计算查询水果的数量
        int fruitCount = fruitService.getFruitCount(keyword);
        //保存到session作用域
        session.setAttribute("fruitList",fruitList);
        session.setAttribute("pageCount",Math.ceil(fruitCount/3.0));
        session.setAttribute("pageNum",pageNum);
        //转发至index.html
        super.processTemplate("index",request,response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
