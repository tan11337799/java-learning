package com.twhupup.demo1;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @Project: Thread
 * @Package: com.twhupup.demo1
 * @Date: 2022/3/24 22:10
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */

//实现多线程同步下载图片
public class TestThread2 extends Thread{
    private String url;//网络图片地址
    private String name;//保存文件名

    public TestThread2(String url, String name) {
        this.url = url;
        this.name = name;
    }

    //加载图片线程的执行体
    @Override
    public void run() {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.downloader(url,name);
        System.out.println(name+"下载完成");
    }

    public static void main(String[] args) {
        TestThread2 t1 = new TestThread2("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fnewbbs-fd.zol-img.com.cn%2Ft_s1200x5000%2Fg6%2FM00%2F07%2F0B%2FChMkKmE3ammIQ_CsAAwcB7PVr-IAATfpQPOV5MADBwf181.jpg&refer=http%3A%2F%2Fnewbbs-fd.zol-img.com.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1650795859&t=55e0ae3360d90c5f921dc98b99fc36ce","output1.jpg");
        TestThread2 t2 = new TestThread2("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fnewbbs-fd.zol-img.com.cn%2Ft_s1200x5000%2Fg6%2FM00%2F07%2F0B%2FChMkKmE3ammIQ_CsAAwcB7PVr-IAATfpQPOV5MADBwf181.jpg&refer=http%3A%2F%2Fnewbbs-fd.zol-img.com.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1650795859&t=55e0ae3360d90c5f921dc98b99fc36ce","output2.jpg");

        t1.start();
        t2.start();
    }
}

class WebDownloader{
    public void downloader(String url,String name){
        try {
            FileUtils.copyURLToFile(new URL(url),new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO异常，downloader方法出现问题");
        }
    }
}
