package com.twhupup.basic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @Project: Reflection
 * @Package: com.twhupup.basic
 * @Date: 2022/4/4 10:02
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public class ReflectionQuestion {
    public static void main(String[] args) throws ClassNotFoundException, IOException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        //根据配置文件re.properties指定信息，创建Cat对象并调用方法hi

        //使用IO文件流读取配置文件（使用Properties类）
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\main\\resources\\re.properties"));
        String classfullpath = properties.get("classfullpath").toString();
        String methodName = properties.get("method").toString();
//        System.out.println(classfullpath);
//        System.out.println(methodName);
        //加载类，返回Class类型对象
        Class cls = Class.forName(classfullpath);
        //通过cls得到加载类的对象实例
        Object o = cls.newInstance();
        Method method = cls.getMethod(methodName);
        method.invoke(o);

        Field nameField = cls.getField("age");
        System.out.println(nameField.get(o));

        Constructor constructor = cls.getConstructor();//括号中指定构造器参数类型，不写表示无参构造器
        System.out.println(constructor);

        Constructor constructor2 = cls.getConstructor(String.class,int.class);//括号中指定构造器参数类型，不写表示无参构造器
        System.out.println(constructor2);

    }
}
