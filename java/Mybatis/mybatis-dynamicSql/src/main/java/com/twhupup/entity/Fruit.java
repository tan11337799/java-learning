package com.twhupup.entity;

/**
 * @Project: mybatis-basic
 * @Package: entity
 * @Date: 2022/6/26 14:56
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public class Fruit {
    private int fid;
    private String fname;
    private int price;
    private int fcount;
    private String remark;

    public Integer getFid() {
        return fid;
    }

    public String getFname() {
        return fname;
    }

    public int getPrice() {
        return price;
    }

    public int getFcount() {
        return fcount;
    }

    public String getRemark() {
        return remark;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setFcount(int fcount) {
        this.fcount = fcount;
    }


    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "fid=" + fid +
                ", fname='" + fname + '\'' +
                ", price=" + price +
                ", fcount=" + fcount +
                ", remark='" + remark + '\'' +
                '}';
    }
}
