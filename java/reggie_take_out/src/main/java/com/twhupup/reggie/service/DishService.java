package com.twhupup.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.twhupup.reggie.dto.DishDto;
import com.twhupup.reggie.entity.Dish;

import java.util.List;

/**
 * @Project: reggie_take_out
 * @Package: com.twhupup.reggie.service.impl
 * @Date: 2022/7/1 10:06
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public interface DishService extends IService<Dish> {
    //新增菜品同时插入菜品的口味数据
    public void saveDishWithFlavor(DishDto dishDto);

    //根据id查询菜品信息和口味信息
    public DishDto getDishByIdWithFlavor(Long id);

    //更新菜品信息同时更新口味信息
    public void updateDishWithFlavor(DishDto dishDto);

    //根据id列表批量修改菜品的状态
    public void batchUpdateDishStatusByIds(Integer status, List<Long> ids);

    //根据id列表批量删除菜品
    public void batchDeleteDishesByIds(List<Long> ids);

}
