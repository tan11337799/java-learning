package com.twhupup.dao;

import com.twhupup.entity.Order;

import java.util.List;

/**
 * @Project: mybatis-multi
 * @Package: com.twhupup.dao
 * @Date: 2022/6/28 19:36
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public interface OrderMapper {
    public List<Order> selectAll();

}
