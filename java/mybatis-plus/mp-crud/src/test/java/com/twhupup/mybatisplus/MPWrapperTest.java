package com.twhupup.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.twhupup.mybatisplus.mapper.UserMapper;
import com.twhupup.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * Project: mybatis-plus
 * Package: com.twhupup.mybatisplus
 * Date: 2022/7/6 11:19
 * Author: Wenhao Tan
 * Version: 1.0
 * License: (C)2022,MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
@SpringBootTest
public class MPWrapperTest {

	@Autowired
	private UserMapper userMapper;

	@Test
	public void test01(){
		QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>();
		userQueryWrapper.like("username","a")
				.isNotNull("password");
		List<User> users = userMapper.selectList(userQueryWrapper);
		System.out.println(users);
	}}
