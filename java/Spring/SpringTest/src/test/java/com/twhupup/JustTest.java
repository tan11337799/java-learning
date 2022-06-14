package com.twhupup;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Project: SpringTest
 * @Package: com.twhupup
 * @Date: 2022/2/23 11:41
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public class JustTest {
    @Test
    public void testbean(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
        Book b = context.getBean("book",Book.class);
        System.out.println(b);
    }
}
