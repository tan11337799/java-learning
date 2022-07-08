package com.wdjr.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
    @RequestMapping("/hello")
    public String success(){
        System.out.println("yes");
        return "success";
    }

    @RequestMapping("/hello1")
    public ModelAndView hello(){
        System.out.println("yes");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("k1","v1");
        modelAndView.setViewName("success");
        return modelAndView;
    }
}
