package com.twhupup.demo1;

/**
 * @Project: Thread
 * @Package: com.twhupup.demo1
 * @Date: 2022/3/28 12:57
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */

//用Runnable使用多个线程同时操作同一个对象
//购买火车票

public class TestThread4 implements Runnable{

    private int ticketNum = 10;

    public void run() {
        while(true){
            if(ticketNum<=0){
                break;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"拿到了第"+ticketNum--+"张票");
        }
    }

    public static void main(String[] args) {
        TestThread4 tt = new TestThread4();
        new Thread(tt,"a").start();
        new Thread(tt,"b").start();
        new Thread(tt,"c").start();
        new Thread(tt,"d").start();
    }
}
