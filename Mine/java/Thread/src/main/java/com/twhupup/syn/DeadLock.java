package com.twhupup.syn;

import jdk.nashorn.internal.ir.CallNode;

/**
 * @Project: Thread
 * @Package: com.twhupup.syn
 * @Date: 2022/4/1 13:24
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public class DeadLock {
    public static void main(String[] args) {
        Makeup a = new Makeup(0, "a");
        Makeup b = new Makeup(1, "b");
        a.start();
        b.start();

    }
}

class Lipstick {

}

class Mirror {

}

class Makeup extends Thread {
    static Lipstick lipstick = new Lipstick();
    static Mirror mirror = new Mirror();

    int choice;
    String girlName;

    Makeup(int choice, String girlName) {
        this.choice = choice;
        this.girlName = girlName;
    }

    @Override
    public void run() {
        try {
            makeup();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void makeup() throws InterruptedException {
        if (choice == 0) {
            synchronized (lipstick) {
                System.out.println(this.getName() + "获得口红的锁");
                Thread.sleep(1000);
            }

            synchronized (mirror) {
                System.out.println(this.getName() + "获得镜子的锁");
            }
        } else {
            synchronized (mirror) {
                System.out.println(this.getName() + "获得镜子的锁");
                Thread.sleep(2000);
            }
            synchronized (lipstick) {
                System.out.println(this.getName() + "获得口红的锁");
            }
        }
    }
}
