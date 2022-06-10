package com.twhupup.method;

import java.lang.reflect.Field;

/**
 * @Project: Reflection
 * @Package: com.twhupup.method
 * @Date: 2022/4/10 19:40
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public class ClassMethod {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        //获取Car类对应的对象
        //<?>表示不确定的Java类型
        Class<?> aClass = Class.forName("com.twhupup.method.Car");
        System.out.println(aClass);//显示class对象属于哪个类
        System.out.println(aClass.getClass());//aClass的运行类型是java.lang.Class
        System.out.println(aClass.getPackage());//通过Class对象得到包名
        System.out.println(aClass.getName());//通过Class对象得到全类名
        Car car = (Car)aClass.newInstance();//通过Class对象创建对象实例
        //通过反射获取属性
        Field brand = aClass.getField("brand");
        System.out.println(brand.get(car));
        //通过反射对属性赋值
        brand.set(car,"奔驰");
        System.out.println(brand.get(car));
        //通过反射获取所有属性
        Field[] fields = aClass.getFields();
        for(Field f:fields){
            System.out.println(f.getName());
        }


    }
}
