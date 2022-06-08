package com.twhupup.StaticPolicy;

import java.util.Map;

/**
 * @Project: Thread
 * @Package: com.twhupup.StaticPolicy
 * @Date: 2022/3/29 22:31
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */

//真实对象和代理对象实现同一个接口
//代理对象要代理真实角色（设置真实角色的属性并传入）
public class TestSP {
    public static void main(String[] args) {
        new Thread(()-> System.out.println("我爱你")).start();
        new WeddingCompany(new You()).HappyMarry();
    }
}

interface Marry{
    void HappyMarry();
}

//真实角色结婚
class You implements Marry{
    @Override
    public void HappyMarry() {
        System.out.println("我要结婚了，很开心！");
    }
}

//代理角色，帮助结婚
class WeddingCompany implements Marry{
    //真实目标角色
    private Marry target;


    public WeddingCompany(Marry target) {
        this.target = target;
    }

    @Override
    public void HappyMarry() {
        before();
        target.HappyMarry();//代理真实对象
        after();
    }

    private void after() {
        System.out.println("结婚之后收尾款");
    }

    public void before(){
        System.out.println("结婚前布置现场");
    }
}