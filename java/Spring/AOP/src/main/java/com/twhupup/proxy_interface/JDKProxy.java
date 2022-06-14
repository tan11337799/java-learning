package com.twhupup.proxy_interface;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @Project: SpringDemo3
 * @Package: com.twhupup
 * @Date: 2022/4/29 21:06
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public class JDKProxy {
    public static void main(String[] args) {
        Class[] interfaces = {UserDao.class};
        UserDaoImpl userDao = new UserDaoImpl();
        UserDao dao = (UserDao)Proxy.newProxyInstance(JDKProxy.class.getClassLoader(), interfaces, new UserDaoProxy(userDao));
        int result = dao.add(1,2);
        System.out.println("结果为"+result);
    }
}

class UserDaoProxy implements InvocationHandler{
    //把需要代理的实现类对象传入（一般使用有参构造传入）
    private Object obj;
    public UserDaoProxy(Object obj){
        this.obj = obj;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //方法前
        System.out.println("在方法之前执行..."+method.getName()+"传入参数..。"+ Arrays.toString(args));
        //被增强的方法执行(使用反射机制）
        Object res = method.invoke(obj, args);
        //方法后
        System.out.println("方法执行后..."+obj);
        return res;
    }
}