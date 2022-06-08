package com.twhupup.demo2;

/**
 * @Project: Thread
 * @Package: com.twhupup.demo2
 * @Date: 2022/3/30 21:22
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public class TestJoin implements Runnable{


    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("vip-"+i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestJoin testJoin = new TestJoin();
        Thread thread = new Thread(testJoin);
        thread.start();

        //主线程
        for (int i = 0; i < 500; i++) {
            if(i==200){
                thread.join();
            }
            System.out.println("main-"+i);
        }
    }
}

