package com.twhupup.demo2;

/**
 * @Project: Thread
 * @Package: com.twhupup.demo2
 * @Date: 2022/3/31 10:51
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public class TestPriority implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"-->"+Thread.currentThread().getPriority());
    }

    public static void main(String[] args) {
        TestPriority testPriority = new TestPriority();
        System.out.println(Thread.currentThread().getName()+"-->"+Thread.currentThread().getPriority());//朱线程优先级
        Thread t1 = new Thread(testPriority);
        Thread t2 = new Thread(testPriority);
        Thread t3 = new Thread(testPriority);
        Thread t4 = new Thread(testPriority);
        Thread t5 = new Thread(testPriority);
        Thread t6 = new Thread(testPriority);

        t1.start();
        t2.setPriority(1);
        t2.start();
        t3.setPriority(4);
        t3.start();
        t4.setPriority(Thread.MAX_PRIORITY);
        t4.start();
        t5.setPriority(6);
        t5.start();
        t6.setPriority(8);
        t6.start();

    }
}
