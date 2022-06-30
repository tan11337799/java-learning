package com.twhupup.dao;

import com.twhupup.entity.User;

import java.util.List;

/**
 * @Project: mybatis-dynamic
 * @Package: com.twhupup.dao
 * @Date: 2022/6/27 14:14
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public interface  UserMapper {
    public void save(User user);
    public User selectById(Integer id);
    public List<User> selectAll();

}
