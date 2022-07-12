package com.twhupup.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.twhupup.reggie.common.R;
import com.twhupup.reggie.entity.Category;
import com.twhupup.reggie.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Project: reggie_take_out
 * Package: com.twhupup.reggie.controller
 * Date: 2022/7/10 13:28
 * Author: Wenhao Tan
 * Version: 1.0
 * License: (C)2022,MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */

@Slf4j
@RestController //@RestController相当于@RequestBody+@Controller，定义该类为Controller类，并接收前端发送的请求
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public R<String> save(@RequestBody Category category) {
        log.info("category:{}", category);
        categoryService.save(category);
        return R.success(null);
    }

    @GetMapping("/page")
    public R<Page<Category>> page(int page, int pageSize) {
        log.info("进行菜品分类的分页查询：page={},pageSize={}", page, pageSize);
        Page<Category> pageInfo = new Page<>(page, pageSize);
        //构造条件构造器用于分页的条件查询
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Category::getSort);
        categoryService.page(pageInfo, queryWrapper);
        return R.success(pageInfo);
    }

    /**
     * 根据id修改分类信息
     * @param category
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody Category category) {
        log.info("需要修改的分类为：{}", category.toString());
        categoryService.updateById(category);
        return R.success(null);
    }

    /**
     * 根据id删除对应分类，如果该分类绑定菜品或套餐则会删除失败
     * @param id
     * @return
     */
    @DeleteMapping
    public R<String> delete(Long id){
        log.info("需要删除分类的id为：{}",id);
        categoryService.remove(id);
        return R.success(null);
    }

    /**
     * 添加菜品（下拉框显示），根据type=?进行分类查询（使用实体类接收，通用性更强）
     * 将查询到的类别进行上传，用于前端获取分类结果
     * @param category
     * @return
     */
    @GetMapping("/list")
    public R<List<Category>> list(Category category){
        LambdaQueryWrapper<Category> categoryLambdaQueryWrapper = new LambdaQueryWrapper<>();
        categoryLambdaQueryWrapper.eq(category.getType()!=null,Category::getType,category.getType())
                .orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);
        List<Category> list = categoryService.list(categoryLambdaQueryWrapper);
        return R.success(list);
    }
}
