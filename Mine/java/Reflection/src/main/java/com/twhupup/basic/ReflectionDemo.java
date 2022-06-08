package com.twhupup.basic;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Project: Reflection
 * @Package: com.twhupup.basic
 * @Date: 2022/4/8 9:45
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public class ReflectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        m1();
        m2();
        m3();
    }

    public static void m1(){
        Cat cat = new Cat();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 90000000; i++) {
            cat.hi();
        }
        long end = System.currentTimeMillis();
        System.out.println("传统方法耗时"+(end-start)+"ms");
    }
    public static void m2() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        Class cls = Class.forName("com.twhupup.basic.Cat");
        Object o = cls.newInstance();
        Method hi = cls.getMethod("hi");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 90000000; i++) {
            hi.invoke(o);
        }
        long end = System.currentTimeMillis();
        System.out.println("反射机制调用耗时"+(end-start)+"ms");
    }
    public static void m3() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        Class cls = Class.forName("com.twhupup.basic.Cat");
        Object o = cls.newInstance();
        Method hi = cls.getMethod("hi");
        hi.setAccessible(true);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 90000000; i++) {
            hi.invoke(o);
        }
        long end = System.currentTimeMillis();
        System.out.println("反射机制调用耗时"+(end-start)+"ms");
    }
}
