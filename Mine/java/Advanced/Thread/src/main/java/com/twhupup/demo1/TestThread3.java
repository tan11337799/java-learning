package com.twhupup.demo1;

/**
 * @Project: Thread
 * @Package: com.twhupup.demo1
 * @Date: 2022/3/25 23:00
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public class TestThread3 implements Runnable{
    public void run() {
        System.out.println("实现Runnable的类");
    }

    public static void main(String[] args) {
        TestThread3 testThread3 = new TestThread3();
        new Thread(testThread3).start();
    }
}
