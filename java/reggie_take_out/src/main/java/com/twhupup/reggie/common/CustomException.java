package com.twhupup.reggie.common;

/**
 * Project: reggie_take_out
 * Package: com.twhupup.reggie.common
 * Date: 2022/7/10 15:07
 * Author: Wenhao Tan
 * Version: 1.0
 * License: (C)2022,MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public class CustomException extends RuntimeException{
    //将提示信息进行输出
    public CustomException(String message){
        super(message);
    }
}
