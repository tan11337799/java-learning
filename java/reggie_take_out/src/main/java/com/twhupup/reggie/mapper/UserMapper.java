package com.twhupup.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.twhupup.reggie.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Project: reggie_take_out
 * Package: com.twhupup.reggie.mapper
 * Date: 2022/7/10 12:19
 * Author: Wenhao Tan
 * Version: 1.0
 * License: (C)2022,MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
