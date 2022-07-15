package com.twhupup.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.twhupup.reggie.common.BaseContext;
import com.twhupup.reggie.common.CustomException;
import com.twhupup.reggie.entity.*;
import com.twhupup.reggie.mapper.OrdersMapper;
import com.twhupup.reggie.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Project: reggie_take_out
 * Package: com.twhupup.reggie.service.impl
 * Date: 2022/7/10 12:21
 * Author: Wenhao Tan
 * Version: 1.0
 * License: (C)2022,MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
@Slf4j
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {
    @Autowired
    private UserService userService;

    @Autowired
    private AddressBookService addressBookService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Override
    public void submit(Orders orders) {
        //获取当前用户购物车中的数据
        Long userId = BaseContext.getCurrentId();
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,userId);
        List<ShoppingCart> shoppingCartList = shoppingCartService.list(queryWrapper);
        if(shoppingCartList==null || shoppingCartList.size()==0){
            throw new CustomException("购物车为空，无法下单");
        }
        //获取该订单的用户数据
        User user = userService.getById(userId);
        //获取该订单的地址数据
        Long addressBookId = orders.getAddressBookId();
        AddressBook addressBook = addressBookService.getById(addressBookId);
        if(addressBook==null){
            throw new CustomException("用户输入的信息有误，不能下单");
        }
        //生成订单号
        long orderId = IdWorker.getId();
        AtomicInteger amount = new AtomicInteger(0);
        //基于购物车构造订单明细表数据
        List<OrderDetail> orderDetailList = shoppingCartList.stream().map(item -> {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(orderId);
            orderDetail.setAmount(item.getAmount());
            orderDetail.setDishFlavor(item.getDishFlavor());
            orderDetail.setDishId(item.getDishId());
            orderDetail.setImage(item.getImage());
            orderDetail.setName(item.getName());
            orderDetail.setNumber(item.getNumber());
            orderDetail.setSetmealId(item.getSetmealId());
            amount.addAndGet(item.getAmount().multiply(new BigDecimal(item.getNumber())).intValue());
            return orderDetail;
        }).collect(Collectors.toList());
        //向订单表中插入数据
        orders.setId(orderId);
        orders.setAddress(addressBook.getDetail());
        orders.setAmount(new BigDecimal(amount.intValue()));
        orders.setCheckoutTime(LocalDateTime.now());
        orders.setConsignee(addressBook.getConsignee());
        orders.setNumber(String.valueOf(orderId));
        orders.setOrderTime(LocalDateTime.now());
        orders.setPayMethod(2);
        orders.setPhone(user.getPhone());
        orders.setStatus(2);
        orders.setUserId(userId);
        orders.setUserName(user.getName());
        this.save(orders);
        orderDetailService.saveBatch(orderDetailList);
        shoppingCartService.remove(queryWrapper);
    }


}
