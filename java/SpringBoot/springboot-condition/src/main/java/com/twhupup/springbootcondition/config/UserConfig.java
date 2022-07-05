package com.twhupup.springbootcondition.config;

// import com.twhupup.springbootcondition.condition.ConditionOnClass;

import com.twhupup.springbootcondition.condition.ConditionOnClass;
import com.twhupup.springbootcondition.domain.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Project: SpringBoot
 * @Package: com.twhupup.springbootcondition.config
 * @Date: 2022/7/4 19:12
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */

@Configuration
public class UserConfig {
    @Bean
    // @Conditional(ClassCondition.class)
    @ConditionOnClass("redis.clients.jedis.Jedis")
    public User user(){
        return new User();
    }
    @Bean
    @ConditionalOnProperty(name="itcast",havingValue = "itheima")
    public User user2(){
        return new User();
    }

    @Bean
    @ConditionalOnMissingBean(name = "redisTemplate")
    public User user3(){
        return new User();
    }
}
