package com.twhupup.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.twhupup.reggie.common.BaseContext;
import com.twhupup.reggie.common.R;
import com.twhupup.reggie.entity.ShoppingCart;
import com.twhupup.reggie.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Project: reggie_take_out
 * Package: com.twhupup.reggie.controller
 * Date: 2022/7/13 17:02
 * Author: Wenhao Tan
 * Version: 1.0
 * License: (C)2022,MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
@RestController
@Slf4j
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;


    @GetMapping("/list")
    public R<List<ShoppingCart>> list() {
        LambdaQueryWrapper<ShoppingCart> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ShoppingCart::getUserId, BaseContext.getCurrentId());
        wrapper.orderByAsc(ShoppingCart::getCreateTime);
        List<ShoppingCart> shoppingCartList = shoppingCartService.list(wrapper);
        return R.success(shoppingCartList);
    }

    @PostMapping("/add")
    public R<ShoppingCart> add(@RequestBody ShoppingCart shoppingCart) {
        //获取当前用户的id
        Long currentId = BaseContext.getCurrentId();
        shoppingCart.setUserId(currentId);
        //先查询购物车是否有重复的(userid+dishid/setmealid联合锁定购物车的内容)，有则number+1，否则插入新纪录
        LambdaQueryWrapper<ShoppingCart> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ShoppingCart::getUserId, currentId);
        Long dishId = shoppingCart.getDishId();
        if (dishId != null) {//添加为菜品
            wrapper.eq(ShoppingCart::getDishId, dishId);
            wrapper.eq(shoppingCart.getDishFlavor() != null, ShoppingCart::getDishFlavor, shoppingCart.getDishFlavor());
        } else {//添加为套餐
            wrapper.eq(ShoppingCart::getSetmealId, shoppingCart.getSetmealId());
        }
        ShoppingCart one = shoppingCartService.getOne(wrapper);
        if (one != null) {//购物车中存在该数据，则让number+=1，修改该数据
            Integer number = one.getNumber();
            one.setNumber(number + 1);
            shoppingCartService.updateById(one);
        } else {//购物车不存在该数据，则设置number为1，将该数据存储到购物车中
            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(LocalDateTime.now());
            shoppingCartService.save(shoppingCart);
            one = shoppingCart;
        }
        return R.success(one);
    }

    /**
     * 清空购物车
     * @return
     */
    @DeleteMapping("/clean")
    public R<String> clean(){
        Long currentId = BaseContext.getCurrentId();
        LambdaQueryWrapper<ShoppingCart> shoppingCartLambdaQueryWrapper = new LambdaQueryWrapper<>();
        shoppingCartLambdaQueryWrapper.eq(ShoppingCart::getUserId,currentId);
        boolean remove = shoppingCartService.remove(shoppingCartLambdaQueryWrapper);
        if(remove){
            return R.success(null);
        }else{
            return R.error("删除失败！");
        }
    }

    @PostMapping("/sub")
    public R<ShoppingCart> sub(@RequestBody ShoppingCart shoppingCart){
        //获取当前用户的id
        Long currentId = BaseContext.getCurrentId();
        shoppingCart.setUserId(currentId);
        //先查询购物车是否有重复的(userid+dishid/setmealid联合锁定购物车的内容)，有则number+1，否则插入新纪录
        LambdaQueryWrapper<ShoppingCart> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ShoppingCart::getUserId, currentId);
        Long dishId = shoppingCart.getDishId();
        if (dishId != null) {//减少菜品
            wrapper.eq(ShoppingCart::getDishId, dishId);
        } else {//减少套餐
            wrapper.eq(ShoppingCart::getSetmealId, shoppingCart.getSetmealId());
        }
        ShoppingCart one = shoppingCartService.getOne(wrapper);
        if (one != null) {//购物车中存在该数据，则让number-=1，修改该数据
            Integer number = one.getNumber();
            if(number>1){
                one.setNumber(number-1);
                shoppingCartService.updateById(one);
            }else if(number==1){
                shoppingCartService.remove(wrapper);
                one.setNumber(0);
            }else{
                return R.error("无效的减少操作");
            }
        }
        return R.success(one);
    }
}
