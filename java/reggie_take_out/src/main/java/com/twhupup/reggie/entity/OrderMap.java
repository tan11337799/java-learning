package com.twhupup.reggie.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Project: reggie_take_out
 * Package: com.twhupup.reggie.entity
 * Date: 2022/7/14 15:00
 * Author: Wenhao Tan
 * Version: 1.0
 * License: (C)2022,MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
@Data
public class OrderMap implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer page;

    private Integer pageSize;

    private String beginTime;

    private String endTime;

    private String number;
}
