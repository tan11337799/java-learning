package com.twhupup.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.twhupup.reggie.common.R;
import com.twhupup.reggie.dto.DishDto;
import com.twhupup.reggie.entity.Category;
import com.twhupup.reggie.entity.Dish;
import com.twhupup.reggie.entity.DishFlavor;
import com.twhupup.reggie.service.CategoryService;
import com.twhupup.reggie.service.DishFlavorService;
import com.twhupup.reggie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Project: reggie_take_out
 * Package: com.twhupup.reggie.controller
 * Date: 2022/7/11 14:04
 * Author: Wenhao Tan
 * Version: 1.0
 * License: (C)2022,MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
@Slf4j
@RestController
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private DishService dishService;

    @Autowired
    private DishFlavorService dishFlavorService;

    /**
     * 新增菜品并提交到数据库
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody DishDto dishDto) {
        log.info("保存菜品数据");
        dishService.saveDishWithFlavor(dishDto);
        return R.success(null);
    }


    /**
     * 功能：对菜品管理进行分页查询
     * 难点：页面要求显示菜品分类的名称，而对应Page<Dish>封装的Dish中只有categoryId
     * 解决：
     * 通过dto类，该类继承原来的实体类，在对象中扩展缺少的属性
     * 通过BeanUtils工具类将查询出的page对象进行拷贝，并单独修改其中的records属性
     * records是一个列表，将列表对象的基本信息再通过BeanUtils进行拷贝，通过service获取对应的菜品名称，返回给拷贝的对象
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
        Page<Dish> pageInfo = new Page<>(page, pageSize);
        Page<DishDto> dishDtoPage = new Page<>();
        //构造条件构造器对象，过滤查询数据
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.like(StringUtils.isNotEmpty(name), Dish::getName, name)
                .orderByDesc(Dish::getUpdateTime);
        dishService.page(pageInfo, dishLambdaQueryWrapper);
        //拷贝原先查询page对象，针对records属性单独处理
        //因为records属性是全部数据的List集合，需要对其中的name进行修改
        BeanUtils.copyProperties(pageInfo, dishDtoPage, "records");
        List<Dish> records = pageInfo.getRecords();
        List<DishDto> list = records.stream().map((item) -> {
            //创建一个新的dto对象，拷贝item原先的值
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(item, dishDto);
            //根据records中的categoryId，使用categoryService获取对应的分类名称
            Long categoryId = item.getCategoryId();
            Category category = categoryService.getById(categoryId);//获取categoryId对应的category对象
            if(category!=null){
                String categoryName = category.getName();
                dishDto.setCategoryName(categoryName);
            }
            return dishDto;
        }).collect(Collectors.toList());
        dishDtoPage.setRecords(list);
        return R.success(dishDtoPage);
    }

    /**
     * 用于根据编辑菜品时选择的id回写数据库中存放的数据（由于数据存放在两个表中，因此需要自定义查询方法）
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<DishDto> getById(@PathVariable Long id){
        log.info("根据id查询菜品信息...");
        DishDto dishDto = dishService.getDishByIdWithFlavor(id);
        if(dishDto!=null){
            return R.success(dishDto);
        }
        return R.error("没有查询到对应信息！");
    }

    /**
     * 用于处理修改菜品时的提交功能，由于修改菜品需要对两张表进行修改，因此自定义修改方法（先清除再添加）
     * @param dishDto
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody DishDto dishDto) {
        log.info("保存菜品数据");
        dishService.updateDishWithFlavor(dishDto);
        return R.success(null);
    }

    /**
     * 单项或批量停售功能
     * @param status
     * @param ids
     * @return
     */
    @PostMapping("/status/{status}")
    public R<String> updateStatus(@PathVariable Integer status, @RequestParam List<Long> ids) {
        log.info("修改菜品状态");
        dishService.batchUpdateDishStatusByIds(status, ids);
            return R.success(null);
    }

    /**
     * 单项或批量删除菜品功能
     * @param ids
     * @return
     */
    @DeleteMapping
    public R<String> batchDelete(@RequestParam List<Long> ids){
        log.info("删除菜品");
        dishService.batchDeleteDishesByIds(ids);
        return R.success(null);
    }

    /**
     * 套餐中根据条件查询菜品数据
     * 在套餐的查询中，要求当前菜品处于在售状态
     * 用途：
     * （1）在修改或新增套餐时，在添加菜品功能中，显示每个分类对应的菜品数组
     * （2）在用户端，显示不同菜品分类对应的菜品时，显示每个分类以及满足在售条件对应的菜品
     * @param dish
     * @return
     */
    @GetMapping("/list")
    public R<List<DishDto>> list(Dish dish){
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //查询条件:(1)在售；（2）分类id为当前请求传入的id；（3）按菜品正向排序，再按更新时间逆序排列。
        dishLambdaQueryWrapper.eq(Dish::getStatus,1)
                .eq(dish.getCategoryId()!=null,Dish::getCategoryId,dish.getCategoryId())
                .orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);
        List<Dish> list = dishService.list(dishLambdaQueryWrapper);
        int count = dishService.count(dishLambdaQueryWrapper);
        if(count==0||list==null){
            return R.error("当前类别下无相关菜品");
        }
        //在查询的菜品中添加对应的口味数据
        List<DishDto> dishDtoList = list.stream().map((item)-> {
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(item, dishDto);
            Long dishId = item.getId();
            LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(DishFlavor::getDishId,dishId);
            List<DishFlavor> dishFlavorList = dishFlavorService.list(queryWrapper);
            dishDto.setFlavors(dishFlavorList);
            return dishDto;
        }).collect(Collectors.toList());
        return R.success(dishDtoList);
    }
}
