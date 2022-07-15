package com.twhupup.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.twhupup.reggie.common.BaseContext;
import com.twhupup.reggie.common.R;
import com.twhupup.reggie.dto.OrdersDto;
import com.twhupup.reggie.entity.OrderDetail;
import com.twhupup.reggie.entity.OrderMap;
import com.twhupup.reggie.entity.Orders;
import com.twhupup.reggie.service.OrderDetailService;
import com.twhupup.reggie.service.OrdersService;
import com.twhupup.reggie.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Project: reggie_take_out
 * Package: com.twhupup.reggie.controller
 * Date: 2022/7/13 16:50
 * Author: Wenhao Tan
 * Version: 1.0
 * License: (C)2022,MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
@RestController
@Slf4j
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrdersService ordersService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders){
        log.info("POST请求:用户提交订单数据");
        ordersService.submit(orders);
        return R.success(null);
    }

    @GetMapping("/page")
    public R<Page<OrdersDto>> page(OrderMap orderMap) {
        //获取页面传入的参数
        Integer page = orderMap.getPage();
        Integer pageSize = orderMap.getPageSize();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime beginTime = null;
        LocalDateTime endTime = null;
        if (orderMap.getBeginTime() != null) {
            beginTime = LocalDateTime.parse(orderMap.getBeginTime(), formatter);
        }
        if (orderMap.getEndTime() != null) {
            endTime = LocalDateTime.parse(orderMap.getEndTime(), formatter);
        }
        //查询指定订单号、起始时间的订单
        String number = orderMap.getNumber();
        Page<Orders> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<Orders> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(number != null, Orders::getNumber, number);
        wrapper.ge(beginTime != null, Orders::getOrderTime, beginTime);
        wrapper.le(endTime != null, Orders::getOrderTime, endTime);
        ordersService.page(pageInfo,wrapper);
        //修改分页参数
        Page<OrdersDto> ordersDtoPageInfo=new Page<>();
        BeanUtils.copyProperties(pageInfo,ordersDtoPageInfo,"records");
        List<Orders> ordersList = pageInfo.getRecords();
        List<OrdersDto> ordersDtoList = ordersList.stream().map(item -> {
            OrdersDto ordersDto = new OrdersDto();
            BeanUtils.copyProperties(item, ordersDto);
            LambdaQueryWrapper<OrderDetail> wrapper1 = new LambdaQueryWrapper<>();
            wrapper1.eq(OrderDetail::getOrderId, item.getId());
            List<OrderDetail> orderDetails = orderDetailService.list(wrapper1);
            ordersDto.setOrderDetails(orderDetails);
            return ordersDto;
        }).collect(Collectors.toList());
        ordersDtoPageInfo.setRecords(ordersDtoList);

        return R.success(ordersDtoPageInfo);
    }


    @GetMapping("/userPage")
    public R<Page<OrdersDto>> page(int page, int pageSize) {
        Page<Orders> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<Orders> ordersWrapper = new LambdaQueryWrapper<>();
        ordersWrapper.eq(Orders::getUserId, BaseContext.getCurrentId());
        ordersWrapper.orderByDesc(Orders::getOrderTime);
        ordersService.page(pageInfo, ordersWrapper);

        Page<OrdersDto> pageDtoInfo = new Page<>();
        BeanUtils.copyProperties(pageInfo, pageDtoInfo, "records");

        List<Orders> ordersList = pageInfo.getRecords();

        List<OrdersDto> ordersDtoList = ordersList.stream().map(item -> {
            OrdersDto ordersDto = new OrdersDto();
            BeanUtils.copyProperties(item, ordersDto);
            LambdaQueryWrapper<OrderDetail> orderDetailWrapper = new LambdaQueryWrapper<>();
            orderDetailWrapper.eq(OrderDetail::getOrderId, item.getId());
            List<OrderDetail> orderDetailList = orderDetailService.list(orderDetailWrapper);
            ordersDto.setOrderDetails(orderDetailList);
            return ordersDto;
        }).collect(Collectors.toList());

        pageDtoInfo.setRecords(ordersDtoList);
        return R.success(pageDtoInfo);
    }

    @PutMapping
    public R<String> updateStatus(@RequestBody Orders orders){
        boolean b = ordersService.updateById(orders);
        if (b) {
            return R.success("修改成功");
        }
        return R.error("未知错误");
    }
}
