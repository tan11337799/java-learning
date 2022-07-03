package com.twhupup.entity;


import java.util.Date;
import java.util.List;

/**
 * @Project: mybatis-basic
 * @Package: entity
 * @Date: 2022/6/26 14:56
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public class User {
    private int id;
    private String username;
    private String password;
    private Date birthday;

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    private List<Role> roleList;


    // private List<Order> orderList;

    // public List<Order> getOrderList() {
    //     return orderList;
    // }
    //
    // public void setOrderList(List<Order> orderList) {
    //     this.orderList = orderList;
    // }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                // ", orderList=" + orderList +
                ", roleList=" + roleList +
                '}';
    }
}
