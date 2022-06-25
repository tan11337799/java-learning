package com.twhupup.servlets;

import com.twhupup.config.SpringConfig;
import com.twhupup.fruit.Fruit;
import com.twhupup.myssm.myspringmvc.ViewBaseServlet;
import com.twhupup.myssm.util.StringUtil;
import com.twhupup.service.FruitService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit.do")
public class EditServlet extends ViewBaseServlet {

    @Override
    public void doGet(HttpServletRequest request , HttpServletResponse response)throws IOException, ServletException {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        FruitService fruitService = context.getBean("fruitService", FruitService.class);
        //通过request.getParameter()获取get请求的信息（存在于url上【对应index.html中超链接的 |edit.do?fid=${fruit.fid}| 】）
        String fidStr = request.getParameter("fid");
        //根据fid获取指定id的fruit对象的信息
        if(StringUtil.isNotEmpty(fidStr)){
            int fid = Integer.parseInt(fidStr);
            Fruit fruit = fruitService.getFruitByFid(fid);
            //将获取的fruit对象保存在request的作用域中
            request.setAttribute("fruit",fruit);
            //将网址转发到"/edit.html"中，request作用域的数据保留
            super.processTemplate("edit",request,response);
        }
    }
}
