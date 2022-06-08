package com.twhupup.demo2;

/**
 * @Project: Thread
 * @Package: com.twhupup.demo2
 * @Date: 2022/3/30 11:48
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public class TestStop implements Runnable{
    private boolean flag = true;

    @Override
    public void run() {
        int i = 0;
        while(flag){
            System.out.println("run Thread"+i++);
        }
    }

    public void stop(){
        flag = false;
    }

    public static void main(String[] args) throws InterruptedException {
        TestStop testStop = new TestStop();
        new Thread(testStop).start();
        Thread.sleep(10);
        for (int i = 0; i < 1000; i++) {
//            System.out.println(i);
//            System.out.println("main"+i);
            if(i==900){
                testStop.stop();
                System.out.println("该线程停止");
            }
        }
    }
}
