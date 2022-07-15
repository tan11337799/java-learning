package com.twhupup.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.twhupup.reggie.common.R;
import com.twhupup.reggie.dto.SetmealDto;
import com.twhupup.reggie.entity.Category;
import com.twhupup.reggie.entity.Setmeal;
import com.twhupup.reggie.service.CategoryService;
import com.twhupup.reggie.service.SetmealDishService;
import com.twhupup.reggie.service.SetmealService;
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
 * Date: 2022/7/11 21:23
 * Author: Wenhao Tan
 * Version: 1.0
 * License: (C)2022,MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */

/**
 * 套餐管理
 */
@Slf4j
@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Autowired
    private SetmealService setmealService;
    @Autowired
    private SetmealDishService setMealDishService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 套餐分页查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page<SetmealDto>> page(int page, int pageSize, String name) {
        //分页构造器对象
        Page<Setmeal> pageInfo = new Page<>(page, pageSize);
        Page<SetmealDto> dtoPage = new Page<>();

        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        //添加查询条件，根据name进行like模糊查询
        queryWrapper.like(name != null, Setmeal::getName, name);
        //添加排序条件，根据更新时间降序排列
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);

        setmealService.page(pageInfo, queryWrapper);

        //对象拷贝
        BeanUtils.copyProperties(pageInfo, dtoPage, "records");
        List<Setmeal> records = pageInfo.getRecords();

        List<SetmealDto> list = records.stream().map((item) -> {
            SetmealDto setmealDto = new SetmealDto();
            //对象拷贝
            BeanUtils.copyProperties(item, setmealDto);
            //分类id
            Long categoryId = item.getCategoryId();
            //根据分类id查询分类对象
            Category category = categoryService.getById(categoryId);
            if (category != null) {
                //分类名称
                String categoryName = category.getName();
                setmealDto.setCategoryName(categoryName);
            }
            return setmealDto;
        }).collect(Collectors.toList());

        dtoPage.setRecords(list);
        return R.success(dtoPage);
    }

    /**
     * 删除套餐
     * 通过自定义的remove方法，删除套餐的同时移除套餐与菜品的关联信息，同时保证在售的套餐不删除
     * @param ids
     * @return
     */
    @DeleteMapping
    public R<String> delete(@RequestParam List<Long> ids) {
        log.info("ids:{}", ids);
        setmealService.removeSetmealWithDish(ids);
        return R.success(null);
    }

    /**
     * 修改套餐售卖状态
     * @param status
     * @param ids
     * @return
     */
    @PostMapping("/status/{status}")
    public R<String> updateStatus(@PathVariable Integer status, @RequestParam List<Long> ids) {
        log.info("修改菜品状态");
        setmealService.batchUpdateSetmealStatusByIds(status, ids);
        return R.success(null);
    }

    /**
     * 根据条件查询对应的套餐
     * 用途：
     * （1）在用户端，根据套餐的分类以及在售状态查询其对应的套餐数组
     * @param setmeal
     * @return
     */
    @GetMapping("/list")
    public R<List<Setmeal>> list(Setmeal setmeal) {
        //条件构造器
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.like(StringUtils.isNotEmpty(setmeal.getName()), Setmeal::getName, setmeal.getName());
        setmealLambdaQueryWrapper.eq(null != setmeal.getCategoryId(), Setmeal::getCategoryId, setmeal.getCategoryId());
        setmealLambdaQueryWrapper.eq(null != setmeal.getStatus(), Setmeal::getStatus, setmeal.getStatus());
        setmealLambdaQueryWrapper.orderByDesc(Setmeal::getUpdateTime);
        List<Setmeal> list = setmealService.list(setmealLambdaQueryWrapper);
        return R.success(list);
    }

    //////////////////////////////////////////////////////////////////////////////////////
    //以下三个方法用于新增、修改套餐(还有一个位于DishController下的list方法，用于获取分类对应的菜品)
    //涉及交互过程：
    //(1)每次在套餐列表上点击新增、修改按钮，客户端（浏览器）会向服务器发送ajax请求(category/list?type=2)，请求获取套餐分类数据展示在下拉框中（在CategoryController的list()中已实现）
    //(2)客户端发送ajax请求(category/list?type=1)，请求获取菜品分类数据并展示到添加菜品的窗口左侧
    //(3)【修改特有】客户端发送ajax请求(setmeal/{id})，请求获取套餐信息回写到页面上 => getById
    //(4)客户端发送ajax请求(dish/list?categoryId={id})，请求获取套餐分类中的菜品信息 => list
    //(5)【新增特有】上传图片(common/upload)
    //(5)对之前上传的图片进行下载(common/download?{img_name})，显示在页面上
    //(6)客户端点击保存按钮发送ajax请求(setmeal)提交新增/修改，将结果保存在数据库中 => save/update
    //////////////////////////////////////////////////////////////////////////////////////
    /**
     * 用于修改菜品，根据url中的id查询对应记录回写数据库中存放的数据（由于数据存放在两个表中，因此需要自定义查询方法）
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<SetmealDto> getById(@PathVariable Long id){
        log.info("GET请求:根据url中的id查询数据并回写");
        SetmealDto dishDto = setmealService.getSetmealByIdWithDish(id);
        if(dishDto!=null){
            return R.success(dishDto);
        }
        return R.error("没有查询到对应信息！");
    }


    /**
     * 新增套餐
     * @param setmealDto
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody SetmealDto setmealDto) {
        log.info("POST请求:新增套餐提交");
        setmealService.saveSetmealWithDish(setmealDto);
        return R.success(null);
    }

    /**
     * 修改套餐
     * @param setmealDto
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody SetmealDto setmealDto) {
        log.info("PUT请求:提交套餐数据");
        setmealService.updateSetmealWithDish(setmealDto);
        return R.success(null);
    }
}
