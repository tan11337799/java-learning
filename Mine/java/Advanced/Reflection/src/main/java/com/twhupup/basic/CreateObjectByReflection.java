package com.twhupup.basic;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @Project: Reflection
 * @Package: com.twhupup.basic
 * @Date: 2022/4/17 13:24
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public class CreateObjectByReflection {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //先获取User类的Class对象
        Class<?> userClass = Class.forName("com.twhupup.basic.User");

        //1.通过public的无参构造器创建实例
        Object o = userClass.newInstance();//newInstance()方法会直接调用无参构造器
        System.out.println(o);
        //2.通过public的有参构造器创建实例
        Constructor<?> constructor = userClass.getConstructor(int.class);//先得到对应的构造器对象
        Object o1 = constructor.newInstance(20);//传入实参，创建实例
        System.out.println(o1);
        //3.通过非public的有参构造器创建实例
        Constructor<?> constructor1 = userClass.getDeclaredConstructor(int.class, String.class);//先得到对应私有构造器
        constructor1.setAccessible(true);//爆破，使用反射可以访问private构造器
        Object o2 = constructor1.newInstance(30, "ming");//传入实参，创建实例
        System.out.println(o2);
    }
}

class User{
    private int age=10;
    private String name="default";

    public User() {
        System.out.println("public无参构造器被调用");
    }

    private User(int age, String name) {
        this.age = age;
        this.name = name;
        System.out.println("age和name的private构造器被调用");
    }

    public User(int age) {
        this.age = age;
        System.out.println("age的public构造器被调用");

    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
