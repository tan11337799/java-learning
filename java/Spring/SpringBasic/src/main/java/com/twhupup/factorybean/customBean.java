package com.twhupup.factorybean;

import org.junit.Test;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Project: SpringTest
 * @Package: com.twhupup.factorybean
 * @Date: 2022/3/7 21:24
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public class customBean {
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        BeanService service = context.getBean("fb",BeanService.class);
        service.testBean();
    }
}

interface BeanService {
    void testBean();
}

class BeanImp implements BeanService {
    @Override
    public void testBean() {
        System.out.println("BeanService的test程序");
    }
}

class factoryBean implements FactoryBean<BeanImp> {
    @Override
    public BeanImp getObject() throws Exception {
        return new BeanImp();
    }

    @Override
    public Class<?> getObjectType() {
        return BeanImp.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}