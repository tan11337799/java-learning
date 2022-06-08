package com.twhupup.lifeCycle;

/**
 * @Project: SpringTest
 * @Package: com.twhupup.lifeCycle
 * @Date: 2022/3/15 16:47
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public class lifeCycleSource {
    public lifeCycleSource(){
        System.out.println("调用无参构造器");
    }

    public String oname;

    public void setOname(String oname) {
        this.oname = oname;
        System.out.println("调用设置属性方法");
    }

    public void initMethod(){
        System.out.println("调用初始化方法");
    }

    public void destroyMethod(){
        System.out.println("执行销毁方法");
    }
}
