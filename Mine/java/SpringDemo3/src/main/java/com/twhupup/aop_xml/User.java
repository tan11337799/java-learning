package com.twhupup.aop_xml;

import org.springframework.stereotype.Component;

/**
 * @Project: SpringDemo3
 * @Package: com.twhupup.aop_xml
 * @Date: 2022/6/8 14:22
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
@Component
public class User {
    public void add(){
//        int res = 10/0;
        System.out.println("add...");
    }
}
