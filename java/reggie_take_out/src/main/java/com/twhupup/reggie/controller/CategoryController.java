package com.twhupup.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.twhupup.reggie.common.R;
import com.twhupup.reggie.entity.Category;
import com.twhupup.reggie.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping
    public R<String> update(@RequestBody Category category) {
        log.info("需要修改的分类为：{}", category.toString());
        categoryService.updateById(category);
        return R.success(null);
    }

    @DeleteMapping
    public R<String> delete(Long id){
        log.info("需要删除分类的id为：{}",id);
        categoryService.remove(id);
        return R.success(null);
    }
}
