package com.twhupup.mybatisplus;

import com.twhupup.mybatisplus.mapper.UserMapper;
import com.twhupup.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
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

        //INSERT INTO user ( id, username, password ) VALUES ( ?, ?, ? )
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    public void testInsert(){
        User user = new User();
        user.setUsername("twh");
        user.setPassword("asd");
        int result = userMapper.insert(user);
        System.out.println(user.getId());
    }

    @Test
    public void testDelete(){
        // int result = userMapper.deleteById(4);
        // System.out.println(result);

        // Map<String,Object> map = new HashMap<>();
        // map.put("id",2);
        // map.put("username","lisi");
        // userMapper.deleteByMap(map);

        List<Long> list = Arrays.asList(1L,2L,3L);
        userMapper.deleteBatchIds(list);
    }

    @Test
    public void testUpdate(){
        //修改用户信息
        //UPDATE user SET username=?, password=? WHERE id=?
        User user = new User();
        user.setId(4L);
        user.setUsername("xca");
        user.setPassword("zxc");
        int result = userMapper.updateById(user);
    }

}
