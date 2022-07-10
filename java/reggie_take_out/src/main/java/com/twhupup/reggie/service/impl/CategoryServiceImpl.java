package com.twhupup.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.twhupup.reggie.common.CustomException;
import com.twhupup.reggie.entity.Category;
import com.twhupup.reggie.entity.Dish;
import com.twhupup.reggie.entity.Setmeal;
import com.twhupup.reggie.mapper.CategoryMapper;
import com.twhupup.reggie.service.CategoryService;
import com.twhupup.reggie.service.DishService;
import com.twhupup.reggie.service.SetMealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Project: reggie_take_out
 * Package: com.twhupup.reggie.service.impl
 * Date: 2022/7/10 12:21
 * Author: Wenhao Tan
 * Version: 1.0
 * License: (C)2022,MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
@Slf4j
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private DishService dishService;
    @Autowired
    private SetMealService setMealService;

    /**
     * 根据id删除分类，删除前判断该id是否绑定菜品或套餐
     * @param id
     */
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId,id);
        //查询分类是否关联菜品
        int countIdInDish = dishService.count(dishLambdaQueryWrapper);
        if(countIdInDish>0){
            throw new CustomException("当前分类下已关联菜品，不能删除！");
        }
        //查询分类是否关联套餐
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        int countIdInSetMeal = setMealService.count(setmealLambdaQueryWrapper);
        if(countIdInSetMeal>0){
            throw new CustomException("当前分类下已关联套餐，不能删除！");
        }
        super.removeById(id);//如果未关联前两者，则使用IService原先的removeById方法
    }
}
