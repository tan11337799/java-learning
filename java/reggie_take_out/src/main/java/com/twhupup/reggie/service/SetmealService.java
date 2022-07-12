package com.twhupup.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.twhupup.reggie.dto.SetmealDto;
import com.twhupup.reggie.entity.Setmeal;

import java.util.List;

/**
 * @Project: reggie_take_out
 * @Package: com.twhupup.reggie.service.impl
 * @Date: 2022/7/1 10:06
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */

public interface SetmealService extends IService<Setmeal> {

    //新增套餐，同时需要保存套餐和菜品的关联关系
    public void saveSetmealWithDish(SetmealDto setmealDto);

    //移除套餐，同时移除套餐与菜品的关联关系
    public void removeSetmealWithDish(List<Long> ids);

    //批量修改套餐的在售状态
    public void batchUpdateSetmealStatusByIds(Integer status, List<Long> ids);

    //通过Id获取套餐和菜品
    public SetmealDto getSetmealByIdWithDish(Long id);

    //修改套餐以及套餐与菜品的关联关系
    public void updateSetmealWithDish(SetmealDto setmealDto);
}
