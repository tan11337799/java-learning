package com.twhupup.springbootcondition.condition;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * @Project: SpringBoot
 * @Package: com.twhupup.springbootcondition.condition
 * @Date: 2022/7/4 19:44
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */

@Target({ElementType.TYPE, ElementType.METHOD}) //表示该注解生效的位置
@Retention(RetentionPolicy.RUNTIME) //表示该注解生效的时间
@Documented //生成javadoc的文档
@Conditional(ClassCondition.class)
public @interface ConditionOnClass {
    String[] value();
}
