package com.twhupup.mybatisplus;

import com.twhupup.mybatisplus.mapper.UserMapper;
import com.twhupup.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Project: mybatis-plus
 * @Package: PACKAGE_NAME
 * @Date: 2022/7/5 16:35
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
@SpringBootTest
public class MPTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectList(){
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }
}
