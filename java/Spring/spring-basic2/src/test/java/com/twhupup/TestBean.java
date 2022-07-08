package com.twhupup;

import com.twhupup.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Project: spring_demo
 * @Package: com.twhupup.testdemo
 * @Date: 2022/2/3 21:43
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public class TestBean {
    @Test
    public void testOuterBean(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
        UserService userService = context.getBean("userService",UserService.class);
        userService.add();
    }
    @Test
    public void testInnerBean(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
        Emp emp = context.getBean("emp", Emp.class);
        System.out.println(emp);
    }
}
