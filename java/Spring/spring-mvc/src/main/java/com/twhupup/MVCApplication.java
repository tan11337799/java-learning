package com.twhupup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Project: Spring
 * Package: com.twhupup
 * Date: 2022/7/6 23:43
 * Author: Wenhao Tan
 * Version: 1.0
 * License: (C)2022,MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.twhupup.controller"})
public class MVCApplication {
    public static void main(String[] args) {
        SpringApplication.run(MVCApplication.class,args);
    }
}
