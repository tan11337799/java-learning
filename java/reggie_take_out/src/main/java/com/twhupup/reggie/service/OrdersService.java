package com.twhupup.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.twhupup.reggie.entity.Orders;

/**
 * Project: reggie_take_out
 * Package: com.twhupup.reggie.service
 * Date: 2022/7/13 16:53
 * Author: Wenhao Tan
 * Version: 1.0
 * License: (C)2022,MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public interface OrdersService extends IService<Orders> {
    public void submit(Orders orders);
}
