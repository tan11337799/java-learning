package com.twhupup.aop_xml;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Project: SpringDemo3
 * @Package: com.twhupup.aop_xml
 * @Date: 2022/6/8 14:23
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
@Component
@Aspect
public class UserProxy {
    //相同切入点抽取
    @Pointcut(value = "execution(* com.twhupup.aop_xml.User.add(..))")
    public void pointdemo(){
    }


    //前置通知
    @Before(value = "pointdemo()")
    public void before(){
        System.out.println("before...");
    }

    //最终通知
    @After(value = "pointdemo()")
    public void after(){
        System.out.println("after...");
    }

    //异常通知
    @AfterThrowing(value = "pointdemo()")
    public void afterThrowing(){
        System.out.println("afterThrowing...");
    }

    //最终通知
    @AfterReturning(value = "pointdemo()")
    public void afterReturning(){
        System.out.println("afterReturning...");
    }

    //环绕通知
    @Around(value = "pointdemo()")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕前...");
        proceedingJoinPoint.proceed();
        System.out.println("环绕后...");
    }


}
