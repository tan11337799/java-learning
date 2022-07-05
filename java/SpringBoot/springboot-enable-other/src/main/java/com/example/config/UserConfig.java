package com.example.config;

import com.example.domain.Role;
import com.example.domain.User;
import org.springframework.context.annotation.Bean;

/**
 * @Project: SpringBoot
 * @Package: com.example.config
 * @Date: 2022/7/4 22:02
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
// @Configuration
public class UserConfig {
    @Bean
    public User user(){
        return new User();
    }
    @Bean
    public Role role(){
        return new Role();
    }
}
