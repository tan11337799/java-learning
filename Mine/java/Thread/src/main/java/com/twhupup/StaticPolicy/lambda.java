package com.twhupup.StaticPolicy;

/**
 * @Project: Thread
 * @Package: com.twhupup.StaticPolicy
 * @Date: 2022/3/30 0:04
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public class lambda {
    //静态成员内部类
    static class Like2 implements ILike{
        @Override
        public void lambda() {
            System.out.println("lambda2");
        }
    }

    public static void main(String[] args) {
        ILike iLike = new Like();
        iLike.lambda();
        iLike = new Like2();
        iLike.lambda();
        //局部内部类
        class Like3 implements ILike{
            @Override
            public void lambda() {
                System.out.println("lambda3");
            }
        }
        iLike = new Like3();
        iLike.lambda();
        //匿名内部类
        new ILike() {
            @Override
            public void lambda() {
                System.out.println("lambda4");
            }
        }.lambda();
        //lambda方法
        iLike = ()-> System.out.println("lambda5");
        iLike.lambda();
    }
}

interface ILike{
    void lambda();
}

class Like implements ILike{
    @Override
    public void lambda() {
        System.out.println("lambda1");
    }
}