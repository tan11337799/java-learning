package com.example.springmybatis.mapper;

import com.example.springmybatis.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Project: SpringBoot
 * @Package: com.example.springmybatis.mapper
 * @Date: 2022/7/3 22:50
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
@Mapper
public interface UserMapper {
    @Select("select * from t_user")
    public List<User> findAll();
}
