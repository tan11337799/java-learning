package com.twhupup.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * Project: mybatis-plus
 * Package: pojo
 * Date: 2022/7/5 16:34
 * Author: Wenhao Tan
 * Version: 1.0
 * License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
@Data
@TableName("t_user")
public class User {
    @TableId(value = "uid",type = IdType.AUTO)
    private Long id;
    private String username;
    private String password;

    private int isDelete;
}
