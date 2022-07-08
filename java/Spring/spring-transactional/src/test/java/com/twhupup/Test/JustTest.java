package com.twhupup.Test;

import com.twhupup.config.TxConfig;
import com.twhupup.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JustTest {
    // @Test
    // public void TestAccountMoney(){
    //     ApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml");
    //     UserService userService = context.getBean("userService", UserService.class);
    //     userService.accountMoney();
    // }

    @Test
    public void TestAccountMoney_Annotation(){
        ApplicationContext context = new AnnotationConfigApplicationContext(TxConfig.class);
        UserService userService = context.getBean("userService", UserService.class);
        userService.accountMoney();
    }
}
