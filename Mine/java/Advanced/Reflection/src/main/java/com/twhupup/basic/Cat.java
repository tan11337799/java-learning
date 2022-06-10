package com.twhupup.basic;

/**
 * @Project: Reflection
 * @Package: com.twhupup
 * @Date: 2022/4/3 23:20
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public class Cat {
    private String name="cat";
    public int age = 10;


    public Cat() {
    }

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void hi(){
//        System.out.println("I am "+name);
    }
    public void cry(){
        System.out.println(name + " is crying");
    }

}
