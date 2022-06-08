package com.twhupup.demo1;

/**
 * @Project: Thread
 * @Package: com.twhupup.demo1
 * @Date: 2022/3/29 19:42
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */

//龟兔赛跑
public class TestThread5 implements Runnable{

    private static String winner;

    public void run() {
        for (int i = 0; i <= 100; i++) {
            if(Thread.currentThread().getName().equals("rabbit")&&(i%10==0)){
                try {
                    Thread.sleep(1);
                    i+=2;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Boolean flag = gameOver(i);
            if(flag==true){
                break;
            }
            System.out.println(Thread.currentThread().getName()+"-->跑了"+i+"步");
        }


    }

    //判断是否完成比赛
    private boolean gameOver(int step){
        if(winner!=null){
            return true;
        }else{
            if(step>=100){
                System.out.println("winner is "+Thread.currentThread().getName());
                return true;
            }
        }
        return false;

    }

    public static void main(String[] args) {
        TestThread5 tt = new TestThread5();
        new Thread(tt,"rabbit").start();
        new Thread(tt,"tortoise").start();
    }
}

