package com.twhupup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Project: Spring
 * Package: com.twhupup.controller
 * Date: 2022/7/6 23:33
 * Author: Wenhao Tan
 * Version: 1.0
 * License: (C)2022,MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
@Controller
@RequestMapping("/demo")
public class UserController {
    @GetMapping("/quick")
    public String save(){
        System.out.println("Controller save running");
        return "/index.html";
    }
}
