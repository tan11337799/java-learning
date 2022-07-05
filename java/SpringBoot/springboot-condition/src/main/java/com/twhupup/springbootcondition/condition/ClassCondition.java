package com.twhupup.springbootcondition.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

/**
 * @Project: SpringBoot
 * @Package: com.twhupup.springbootcondition.condition
 * @Date: 2022/7/4 19:30
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public class ClassCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // boolean flag = true;
        // //判断Jedis.class文件是否存在
        // try {
        //     Class<?> aClass = Class.forName("redis.clients.jedis.Jedis");
        // } catch (ClassNotFoundException e) {
        //     flag=false;
        // }
        // return flag;
        Map<String, Object> map = metadata.getAnnotationAttributes(ConditionOnClass.class.getName());
        String[] value = (String[])map.get("value");
        boolean flag = true;
        for(String className:value){
            try {
                Class<?> aClass = Class.forName(className);
            } catch (ClassNotFoundException e) {
                flag=false;
            }
        }
        return true;
    }
}
