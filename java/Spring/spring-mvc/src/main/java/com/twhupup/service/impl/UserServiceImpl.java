package com.twhupup.service.impl;

import com.twhupup.dao.UserDao;
import com.twhupup.service.UserService;

import javax.annotation.Resource;

/**
 * Project: Spring
 * Package: com.twhupup.service.impl
 * Date: 2022/7/6 23:37
 * Author: Wenhao Tan
 * Version: 1.0
 * License: (C)2022,MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    public void save() {
        userDao.save();
    }
}
