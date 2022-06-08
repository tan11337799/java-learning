package com.twhupup.demo2;

/**
 * @Project: Thread
 * @Package: com.twhupup.demo2
 * @Date: 2022/3/30 21:57
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public class TestState {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("//////");
        });
        Thread.State state = thread.getState();
        System.out.println(state);
        thread.start();//启动线程
        state = thread.getState();
        System.out.println(state);
        while(state != Thread.State.TERMINATED){
            Thread.sleep(100);
            state = thread.getState();
            System.out.println(state);
        }
    }
}
