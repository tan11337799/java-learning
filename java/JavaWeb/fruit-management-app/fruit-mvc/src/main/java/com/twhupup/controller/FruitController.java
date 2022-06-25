package com.twhupup.controller;

import com.twhupup.config.SpringConfig;
import com.twhupup.entity.Fruit;
import com.twhupup.myssm.util.StringUtil;
import com.twhupup.service.FruitService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Project: fruit-management-app
 * @Package: com.twhupup.servlets
 * @Date: 2022/6/23 20:42
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */

@Controller(value = "fruit")
public class FruitController {

    //通过配置类获取容器中service的对象
    private FruitService getFruitService() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        return context.getBean("fruitService", FruitService.class);
    }

    private String index(String oper, String keyword,Integer pageNum, HttpServletRequest request) {
        HttpSession session = request.getSession();
        //在中央控制器中，使用数组存放request获取的属性，如果未获取则相应参数为null，因此需要该判断
        if(pageNum==null){
            pageNum = 1;
        }
        if (StringUtil.isNotEmpty(oper) && oper.equals("search")) {
            pageNum = 1;
            if(StringUtil.isEmpty(keyword)){
                keyword = "";
            }
            session.setAttribute("keyword", keyword);
        } else {
            Object keywordObj = session.getAttribute("keyword");
            if(keywordObj!=null){
                keyword = (String)keywordObj ;
            }else{
                keyword = "" ;
            }
        }
        FruitService fruitService = getFruitService();
        List<Fruit> fruitList = fruitService.findAllFruit(keyword, pageNum);
        int fruitCount = fruitService.getFruitCount(keyword);
        session.setAttribute("fruitList", fruitList);
        session.setAttribute("pageCount", (int) Math.ceil(fruitCount / 3.0));
        session.setAttribute("pageNum", pageNum);
        return "index";
    }

    private String add(String fname, Integer price, Integer fcount, String remark) {
        FruitService fruitService = getFruitService();
        fruitService.addFruit(new Fruit(0, fname, price, fcount, remark));
        return "redirect:fruit.do";
    }

    private String edit(Integer fid, HttpServletRequest request) {
        FruitService fruitService = getFruitService();
        if (fid!=null) {
            Fruit fruit = fruitService.getFruitByFid(fid);
            request.setAttribute("fruit", fruit);
            return "edit";
        }
        return "error";
    }

    private String del(Integer fid) {
        FruitService fruitService = getFruitService();
        if (fid!=null) {
            fruitService.delFruit(fid);
            return "redirect:fruit.do";
        }
        return "error";
    }

    private String update(Integer fid, String fname, Integer price, Integer fcount, String remark) {
        FruitService fruitService = getFruitService();
        fruitService.updateFruit(new Fruit(fid, fname, price, fcount, remark));
        return "redirect:fruit.do";
    }
}

