package com.example.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Project: SpringBoot
 * @Package: com.example.config
 * @Date: 2022/7/5 11:26
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
@Import(UserConfig.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableUser {
}
