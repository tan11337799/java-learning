package com.twhupup.demo2;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Project: Thread
 * @Package: com.twhupup.demo2
 * @Date: 2022/3/30 14:56
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public class TestSleep2 {
    public static void timedown() throws InterruptedException {
        int num = 10;
        while(true){
            Thread.sleep(1000);

            if(num<0){
                break;
            }else if(num==0){
                System.out.println("倒计时结束");
            }else{
                System.out.println(num--);
            }
        }
    }

    public static void main(String[] args) {
        Date startTime = new Date(System.currentTimeMillis());

        while(true){
            try {
                Thread.sleep(1000);
                System.out.println(new SimpleDateFormat("HH:mm:ss").format(startTime));
                startTime = new Date(System.currentTimeMillis());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
