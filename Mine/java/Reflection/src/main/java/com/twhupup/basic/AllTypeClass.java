package com.twhupup.basic;

import java.io.Serializable;

/**
 * @Project: Reflection
 * @Package: com.twhupup.basic
 * @Date: 2022/4/12 16:09
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public class AllTypeClass {
    public static void main(String[] args) {

        Class<String> c1 = String.class;
        Class<Serializable> c2 = Serializable.class;
        Class<Integer[]> c3 = Integer[].class;
        Class<Deprecated> c4 = Deprecated.class;
        Class<Thread.State> c5 = Thread.State.class;
        Class<Void> c6 = void.class;
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println(c4);
        System.out.println(c5);
        System.out.println(c6);
    }
}
