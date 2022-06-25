package com.twhupup.test;

import org.junit.Test;

import java.lang.reflect.Method;

public class JustTest {
    @Test
    public void testCeil(){
        System.out.println(Math.ceil(3.0/2));
    }

    @Test
    public void testFlection(){
        Method[] declaredMethods = this.getClass().getDeclaredMethods();
        for(Method method:declaredMethods){
            System.out.println(method.getName());
        }
    }
}
