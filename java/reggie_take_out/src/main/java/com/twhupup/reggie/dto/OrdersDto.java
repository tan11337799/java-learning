package com.twhupup.reggie.dto;

import com.twhupup.reggie.entity.OrderDetail;
import com.twhupup.reggie.entity.Orders;
import lombok.Data;

import java.util.List;

/**
 * Project: reggie_take_out
 * Package: com.twhupup.reggie.dto
 * Date: 2022/7/13 16:50
 * Author: Wenhao Tan
 * Version: 1.0
 * License: (C)2022,MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
@Data
public class OrdersDto extends Orders {

    private List<OrderDetail> orderDetails;

}
