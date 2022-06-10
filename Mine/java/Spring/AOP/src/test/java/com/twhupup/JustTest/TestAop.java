package com.twhupup.JustTest;

import com.twhupup.aop_xml.User;
import com.twhupup.aop_xml_exercise.NeedEnhance;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Project: SpringDemo3
 * @Package: com.twhupup.JustTest
 * @Date: 2022/6/8 15:30
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */

public class TestAop {
    @Test
    public void testAop(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
        User user = context.getBean("user", User.class);
        user.add();
    }

    @Test
    public void testAop_2(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
        NeedEnhance object = context.getBean("needEnhance", NeedEnhance.class);
        object.print();
    }
}
