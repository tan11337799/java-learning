package com.twhupup.reggie.dto;

import com.twhupup.reggie.entity.User;
import lombok.Data;

/**
 * Project: reggie_take_out
 * Package: com.twhupup.reggie.dto
 * Date: 2022/7/12 16:07
 * Author: Wenhao Tan
 * Version: 1.0
 * License: (C)2022,MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
@Data
public class UserDto extends User {
    private String code;
}
