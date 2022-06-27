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
    private Integer fid;
    private String fname;
    private Integer price;
    private Integer fcount;
    private String remark;

    // public Integer getFid() {
    //     return fid;
    // }

    public void setFid(Integer fid) {
        this.fid = fid;
    }


    public void setFname(String fname) {
        this.fname = fname;
    }


    public void setPrice(Integer price) {
        this.price = price;
    }



    public void setFcount(Integer fcount) {
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
