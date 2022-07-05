package com.twhupup.mybatisplus.pojo;

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
public class User {
    private Integer id;
    private String username;
    private String password;
}
