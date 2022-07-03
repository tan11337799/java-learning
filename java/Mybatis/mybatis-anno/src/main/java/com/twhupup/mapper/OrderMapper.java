package com.twhupup.mapper;

import com.twhupup.entity.Order;
import com.twhupup.entity.User;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

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
    @Select("select * from orders")
    @Results({
            @Result(column = "oid", property = "id"),
            @Result(column = "ordertime", property = "ordertime"),
            @Result(column = "total", property = "total"),
            @Result(
                    property = "user", //要封装的属性名称
                    column = "uid", //根据哪个字段查询
                    javaType = User.class, //封装的实体类
                    one = @One(select = "com.twhupup.mapper.UserMapper.findById"))//查询某个接口的方法获取数据
    })
    public List<Order> findAll();

    // @Select("select *,o.id oid from orders o,user u where o.uid=u.id")
    // @Results({
    //         @Result(column = "oid",property = "id"),
    //         @Result(column = "ordertime",property = "ordertime"),
    //         @Result(column = "total",property = "total"),
    //         @Result(column = "uid",property = "user.id"),
    //         @Result(column = "username",property = "user.username"),
    //         @Result(column = "password",property = "user.password"),
    //         @Result(column = "birthday",property = "user.birthday")
    // })
    // public List<Order> findAll();

    @Select("select * from orders where uid=#{uid}")
    public Order findByUid(int uid);
}
