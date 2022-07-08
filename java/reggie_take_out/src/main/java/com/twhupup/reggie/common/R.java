package com.twhupup.reggie.common;

import lombok.Data;
import java.util.HashMap;
import java.util.Map;

/**
 * 通用返回结果类，服务器响应的数据最终会封装为该对象，最后由SpringMVC封装为JSON格式
 * @param <T>
 */
@Data
public class R<T> {

    private Integer code; //编码：1成功，0和其它数字为失败

    private String msg; //错误信息

    private T data; //数据

    private Map<String,Object> map = new HashMap<>(); //动态数据

    public static <T> R<T> success(T object) {//登陆成功，返回成功的实体类对象、设定code=1
        R<T> r = new R<T>();
        r.data = object;
        r.code = 1;
        return r;
    }

    public static <T> R<T> error(String msg) {//登陆失败，返回失败信息、设定code=0
        R r = new R();
        r.msg = msg;
        r.code = 0;
        return r;
    }

    public R<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

}
