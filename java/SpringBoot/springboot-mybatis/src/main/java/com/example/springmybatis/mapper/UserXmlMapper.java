package com.example.springmybatis.mapper;

import com.example.springmybatis.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Project: SpringBoot
 * @Package: com.example.springmybatis.mapper
 * @Date: 2022/7/4 12:39
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
@Mapper
public interface UserXmlMapper {
    public List<User> findAll();
}
