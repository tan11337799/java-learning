package com.twhupup.autowire;

/**
 * @Project: SpringTest
 * @Package: com.twhupup.autowire
 * @Date: 2022/3/16 21:46
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public class Emp {
    private Dept dept;

//    public void setDept(Dept dept) {
//        this.dept = dept;
//    }

    @Override
    public String toString() {
        return "Emp{" +
                "dept=" + dept +
                '}';
    }

    public void test(){
        System.out.println(dept);
    }
}
