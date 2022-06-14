package com.twhupup.aop_xml_exercise;

import org.springframework.stereotype.Component;

/**
 * @Project: SpringDemo3
 * @Package: com.twhupup.aop_xml_exercise
 * @Date: 2022/6/8 15:34
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
@Component
public class NeedEnhance {
    public void print(){
        System.out.println("需要增强的方法");
    }
}
