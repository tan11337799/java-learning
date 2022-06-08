package com.twhupup.demo3;

/**
 * @Project: Thread
 * @Package: com.twhupup.demo3
 * @Date: 2022/3/31 22:22
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */

//不安全的取钱
//两个人去银行取钱
public class UnsafeBank {
    public static void main(String[] args) {
        Account account = new Account(100,"结婚基金");
        Drawing you = new Drawing(account, 50, "你");
        Drawing girlfriend = new Drawing(account, 100, "女朋友");
        you.start();
        girlfriend.start();
    }
}

//账户
class Account {
    int money;//余额
    String name;//卡名

    public Account(int money, String name) {
        this.money = money;
        this.name = name;
    }
}

class Drawing extends Thread {
    private Account account;//账户
    private int drawingMoney;//取钱
    private int cash;//手里的钱

    public Drawing(Account account, int drawingMoney, String name) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    @Override
    public void run() {
        if (account.money - drawingMoney < 0) {
            System.out.println(Thread.currentThread().getName() + "余额不足");
            return;
        }

        System.out.println(this.getName()+"开始取钱");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        account.money = account.money - drawingMoney;
        cash = cash + drawingMoney;

        System.out.println(account.name+"被"+this.getName()+"取钱后账户余额为：" + account.money);
        System.out.println(this.getName()+"手里的钱：" + cash);

    }
}




















