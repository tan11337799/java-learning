package com.twhupup.demo3;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Project: Thread
 * @Package: com.twhupup.demo3
 * @Date: 2022/3/31 21:43
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public class UnsafeBuyTicket {
    public static void main(String[] args) {
        BuyTicket buyTicket = new BuyTicket();
        new Thread(buyTicket,"me").start();
        new Thread(buyTicket,"he").start();
        new Thread(buyTicket,"she").start();
    }
}

class BuyTicket implements Runnable{
    private int ticketNum = 10;
    boolean flag = true;

    private final ReentrantLock lock  = new ReentrantLock();

    @Override
    public void run() {
        while(flag){
            lock.lock();
            buy();
            lock.unlock();
        }
    }

    private void buy(){
        if(ticketNum<=0){
            flag = false;
            return;
        }
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"拿到"+ticketNum--);
    }
}
