package com.twhupup.aop_xml_exercise;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @Project: SpringDemo3
 * @Package: com.twhupup.aop_xml_exercise
 * @Date: 2022/6/8 15:34
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
@Aspect
@Component
public class Enhance {
    @After(value="execution(* com.twhupup.aop_xml_exercise.NeedEnhance.print())")
    public void after(){
        System.out.println("after...");
    }
}
