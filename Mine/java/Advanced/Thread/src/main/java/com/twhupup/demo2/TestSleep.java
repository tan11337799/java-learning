package com.twhupup.demo2;

import com.twhupup.demo1.TestThread4;

/**
 * @Project: Thread
 * @Package: com.twhupup.demo2
 * @Date: 2022/3/30 14:52
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */

public class TestSleep implements Runnable {
    private int ticketNum = 10;


    public void run() {
        while(true){
            if(ticketNum<=0){
                break;
            }

            System.out.println(Thread.currentThread().getName()+"拿到了第"+ticketNum--+"张票");
        }
    }

    public static void main(String[] args) {
        TestSleep tt = new TestSleep();
        new Thread(tt,"a").start();
        new Thread(tt,"b").start();
        new Thread(tt,"c").start();
        new Thread(tt,"d").start();
    }
}
