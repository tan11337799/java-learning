package com.twhupup.reggie.common;

/**
 * Project: reggie_take_out
 * Package: com.twhupup.reggie.common
 * Date: 2022/7/10 11:37
 * Author: Wenhao Tan
 * Version: 1.0
 * License: (C)2022,MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */

/**
 * 基于ThreadLocal封装工具类，用户保存和获取当前登录用户id
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    /**
     * 设置值
     * @param id
     */
    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    /**
     * 获取值
     * @return
     */
    public static Long getCurrentId(){
        return threadLocal.get();
    }
}
