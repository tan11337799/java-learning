package com.twhupup.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.twhupup.reggie.common.CustomException;
import com.twhupup.reggie.dto.DishDto;
import com.twhupup.reggie.entity.Dish;
import com.twhupup.reggie.entity.DishFlavor;
import com.twhupup.reggie.mapper.DishMapper;
import com.twhupup.reggie.service.DishFlavorService;
import com.twhupup.reggie.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Project: reggie_take_out
 * Package: com.twhupup.reggie.service.impl
 * Date: 2022/7/10 12:21
 * Author: Wenhao Tan
 * Version: 1.0
 * License: (C)2022,MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */


@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
    @Autowired
    private DishFlavorService dishFlavorService;

    //根据id查询菜品信息和口味信息
    public DishDto getDishByIdWithFlavor(Long id) {
        Dish dish = this.getById(id);
        DishDto dishDto = new DishDto();
        BeanUtils.copyProperties(dish,dishDto);

        LambdaQueryWrapper<DishFlavor> dishFlavorLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishFlavorLambdaQueryWrapper.eq(DishFlavor::getDishId,dish.getId());
        List<DishFlavor> list = dishFlavorService.list(dishFlavorLambdaQueryWrapper);
        dishDto.setFlavors(list);
        return dishDto;
    }

    //新增菜品同时插入菜品的口味数据
    @Transactional
    public void saveDishWithFlavor(DishDto dishDto) {
        //保存菜品的基本信息到菜品表
        this.save(dishDto);
        //获取菜品id以及菜品口味list（该flavors没有dishId）
        List<DishFlavor> flavors = dishDto.getFlavors();
        //利用流将菜品口味list的每个元素赋予dishId，再将该集合转为list
        flavors.stream().map((item) -> {
            item.setDishId(dishDto.getId());
            return item;
        }).collect(Collectors.toList());
        //保存 设置dishId的菜品口味数据 到 菜品口味表
        dishFlavorService.saveBatch(flavors);
    }


    //更新菜品信息同时更新口味信息
    @Transactional
    public void updateDishWithFlavor(DishDto dishDto) {
        //更新dish表的基本信息
        this.updateById(dishDto);
        //清理口味表中当前菜品的对应口味数据
        LambdaQueryWrapper<DishFlavor> dishFlavorLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishFlavorLambdaQueryWrapper.eq(DishFlavor::getDishId,dishDto.getId());
        dishFlavorService.remove(dishFlavorLambdaQueryWrapper);
        //添加当前提交的口味数据
        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors.stream().map((item) -> {
            item.setDishId(dishDto.getId());
            return item;
        }).collect(Collectors.toList());
        dishFlavorService.saveBatch(flavors);
    }

    /**
     * 批量修改数据
     * @param status
     * @param ids
     * @return
     */
    @Override
    public void batchUpdateDishStatusByIds(Integer status, List<Long> ids) {
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(ids!=null,Dish::getId,ids);
        List<Dish> list = this.list(queryWrapper);
        //如果存在该id
        if(list!=null){
            for(Dish dish:list){
                dish.setStatus(status);
                this.updateById(dish);
            }
        }else{
            throw new CustomException("sql未知错误，未读取到菜品！");
        }
    }

    @Override
    public void batchDeleteDishesByIds(List<Long> ids) {
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Dish::getId,ids);
        queryWrapper.eq(Dish::getStatus,1);

        int count = this.count(queryWrapper);
        if(count > 0){
            //如果不能删除，抛出一个业务异常
            throw new CustomException("该菜品正在售卖中，不能删除");
        }
        //如果可以删除，先删除菜品表中的数据
        this.removeByIds(ids);

        //delete from dish_flavor where dish_id in (1,2,3)
        LambdaQueryWrapper<DishFlavor> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(DishFlavor::getDishId,ids);
        //删除关系表中的数据
        dishFlavorService.remove(lambdaQueryWrapper);
    }
}
