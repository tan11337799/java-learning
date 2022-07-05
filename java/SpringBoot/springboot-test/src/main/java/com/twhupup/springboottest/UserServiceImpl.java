package com.twhupup.springboottest;

import org.springframework.stereotype.Service;

/**
 * @Project: SpringBoot
 * @Package: com.twhupup.springboottest
 * @Date: 2022/7/3 14:43
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
@Service
public class UserServiceImpl implements UserService{
    @Override
    public void add() {
        System.out.println("add...");
    }
}
