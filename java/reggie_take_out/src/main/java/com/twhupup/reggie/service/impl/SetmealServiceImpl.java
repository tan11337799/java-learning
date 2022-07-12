package com.twhupup.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.twhupup.reggie.common.CustomException;
import com.twhupup.reggie.dto.SetmealDto;
import com.twhupup.reggie.entity.Setmeal;
import com.twhupup.reggie.entity.SetmealDish;
import com.twhupup.reggie.mapper.SetMealMapper;
import com.twhupup.reggie.service.SetmealDishService;
import com.twhupup.reggie.service.SetmealService;
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
public class SetmealServiceImpl extends ServiceImpl<SetMealMapper, Setmeal> implements SetmealService {
    @Autowired
    private SetmealDishService setmealDishService;

    /**
     * 新增套餐，同时需要保存套餐和菜品的关联关系
     * @param setmealDto
     */
    @Transactional
    public void saveSetmealWithDish(SetmealDto setmealDto) {
        //保存套餐的基本信息，操作setmeal，执行insert操作
        //由于service的泛型设置为setmeal，因此this.xxx只会对setmeal属性进行操作，而不会对setmealDto扩展的属性进行处理
        this.save(setmealDto);
        //获取关联关系列表
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        //由于网页提交的setmealDishes属性只包含dishId/name/price，而丧失了与套餐之间的关联关系（外键），因此需要给每个菜品添加对应的套餐编号
        setmealDishes.stream().map((item) -> {
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());

        //将网页传入的setmealDishes（修改版）的对应数据添加到setmealDish表,执行insert操作
        setmealDishService.saveBatch(setmealDishes);
    }

    /**
     * 删除套餐，同时需要删除套餐和菜品的关联数据
     * @param ids
     */
    @Transactional
    public void removeSetmealWithDish(List<Long> ids) {
        //select count(*) from setmeal where id in (1,2,3) and status = 1
        //根据传入的id列表和在售的条件查询套餐信息，将在售的套餐数量进行保存
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Setmeal::getId,ids);
        queryWrapper.eq(Setmeal::getStatus,1);
        int count = this.count(queryWrapper);
        //如果选择的套餐中存在在售的套餐，则说明不可删除，抛出异常
        if(count > 0){
            throw new CustomException("存在套餐正在售卖中，删除失败！");
        }
        //如果不存在在售的套餐则删除套餐表中的数据
        this.removeByIds(ids);

        //delete from setmeal_dish where setmeal_id in (1,2,3)
        //查询关系表中，setmealId为列表中id的数据项
        LambdaQueryWrapper<SetmealDish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(SetmealDish::getSetmealId,ids);
        //删除关系表中的数据
        setmealDishService.remove(lambdaQueryWrapper);
    }

    /**
     * 批量修改套餐的售卖状态
     * @param status
     * @param ids
     */
    @Override
    public void batchUpdateSetmealStatusByIds(Integer status, List<Long> ids) {
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(ids!=null,Setmeal::getId,ids);
        List<Setmeal> list = this.list(queryWrapper);
        //如果存在该id
        if(list!=null){
            for(Setmeal setmeal:list){
                setmeal.setStatus(status);
                this.updateById(setmeal);
            }
        }else{
            throw new CustomException("sql未知错误，未读取到菜品！");
        }
    }

    /**
     * 修改套餐信息
     * @param setmealDto
     */
    //更新套餐信息同时更新套餐菜品关系表
    @Transactional
    public void updateSetmealWithDish(SetmealDto setmealDto) {
        //更新套餐表的基本信息
        this.updateById(setmealDto);
        //清理套餐菜品关系表的菜品信息
        LambdaQueryWrapper<SetmealDish> setmealDishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealDishLambdaQueryWrapper.eq(SetmealDish::getSetmealId,setmealDto.getId());
        setmealDishService.remove(setmealDishLambdaQueryWrapper);
        //添加当前提交的菜品list
        List<SetmealDish> dishes = setmealDto.getSetmealDishes();
        //由于通过网页数据获取的setmealDishes对象不包括与setmeal的映射关系，因此需要添加关联
        dishes.stream().map((item) -> {
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());
        setmealDishService.saveBatch(dishes);
    }


    /**
     * 根据id查询套餐数据以及套餐菜品表数据
     * @param id
     * @return
     */
    //根据套餐id查询套餐信息和对应的菜品信息，保存在dto对象中
    public SetmealDto getSetmealByIdWithDish(Long id) {
        Setmeal setmeal = this.getById(id);
        SetmealDto setmealDto = new SetmealDto();
        BeanUtils.copyProperties(setmeal,setmealDto);
        //查询套餐菜品关系表，根据套餐id获取SetmealDishes属性
        LambdaQueryWrapper<SetmealDish> setmealDishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealDishLambdaQueryWrapper.eq(SetmealDish::getSetmealId,setmeal.getId());
        List<SetmealDish> list = setmealDishService.list(setmealDishLambdaQueryWrapper);
        setmealDto.setSetmealDishes(list);
        return setmealDto;
    }


}
