package com.twhupup.basic;

import com.twhupup.method.Car;

/**
 * @Project: Reflection
 * @Package: com.twhupup.basic
 * @Date: 2022/4/11 23:36
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022,MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 *
 */

public class GetClassObject{
    public static void main(String[] args) throws ClassNotFoundException {
        //1.Class.forName(path)
        String path = "com.twhupup.method.Car";
        Class<?> c1 = Class.forName(path);
        System.out.println(c1);
        //2.类名.class
        Class<Car> c2 = Car.class;
        System.out.println(c2);
        //3.对象.getClass
        Car c3 = new Car();
        System.out.println(c3.getClass());
        //4.在类加载器得到类的Class对象
        ClassLoader classLoader = c3.getClass().getClassLoader();
        Class<?> c4 = classLoader.loadClass(path);
        System.out.println(c4);
    }

}