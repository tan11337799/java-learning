package com.twhupup.reggie.dto;

import com.twhupup.reggie.entity.Dish;
import com.twhupup.reggie.entity.DishFlavor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * dto:数据传输对象
 * 作用：用于展示层和服务层之间的数据传输，这边用于对接收的前端数据进行扩展进而可以接受该数据
 */

@Data
public class DishDto extends Dish {//继承Dish,相当于为Dish扩展接收flavors数组的功能
    //用于接收前端发送的flavors参数
    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
