package com.twhupup;

import com.twhupup.autowire.Emp;
import com.twhupup.collectiontype.Course;
import com.twhupup.collectiontype.Stu;
import com.twhupup.collectiontype.Book;
import com.twhupup.lifeCycle.lifeCycleSource;
import com.twhupup.scope.scopeSource;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * @Project: SpringTest
 * @Package: com.twhupup
 * @Date: 2022/2/23 23:32
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public class JustTest {
    @Test
    public void testStu(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        Stu stu = context.getBean("stu",Stu.class);
        System.out.println(stu);
    }

    @Test
    public void testBook(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml");
        Book book = context.getBean("book",Book.class);
        System.out.println(book);
    }

    @Test
    public void testMyBean(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean3.xml");
        Course course = context.getBean("MyBean", Course.class);
        System.out.println(course);
    }

    @Test
    public void testScope(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean4.xml");
        scopeSource ss = context.getBean("ss", scopeSource.class);
        scopeSource ss1 = context.getBean("ss", scopeSource.class);
        scopeSource ss2 = context.getBean("ss", scopeSource.class);
        System.out.println(ss);
        System.out.println(ss1);
        System.out.println(ss2);
    }

    @Test
    public void testLifeCycle(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean4.xml");
        lifeCycleSource lc = context.getBean("lc",lifeCycleSource.class);
        System.out.println("获取bean对象");
        context.close();
    }

    @Test
    public void testAutowire(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean5.xml");
        Emp emp = context.getBean("emp",Emp.class);
        System.out.println(emp);
    }
}
