package com.twhupup.reggie.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Project: reggie_take_out
 * Package: com.twhupup.reggie.entity
 * Date: 2022/7/13 16:52
 * Author: Wenhao Tan
 * Version: 1.0
 * License: (C)2022,MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
@Data
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    //名称
    private String name;

    //订单id
    private Long orderId;


    //菜品id
    private Long dishId;


    //套餐id
    private Long setmealId;


    //口味
    private String dishFlavor;


    //数量
    private Integer number;

    //金额
    private BigDecimal amount;

    //图片
    private String image;
}
