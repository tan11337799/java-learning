package com.twhupup.testAutoWired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Project: SpringBoot
 * @Package: com.twhupup.testAutoWired
 * @Date: 2022/7/3 15:33
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
@Service
public class test {
    @Autowired
    private RedisTemplate redisTemplate;
}
