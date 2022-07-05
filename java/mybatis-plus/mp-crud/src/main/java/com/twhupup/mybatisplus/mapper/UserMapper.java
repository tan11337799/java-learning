package com.twhupup.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.twhupup.mybatisplus.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * Project: Mybatis-plus
 * Package: mapper
 * Date: 2022/7/5 16:31
 * Author: Wenhao Tan
 * Version: 1.0
 * License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
}
