package com.twhupup.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.twhupup.reggie.entity.Dish;
import com.twhupup.reggie.mapper.DishMapper;
import com.twhupup.reggie.service.DishService;
import org.springframework.stereotype.Service;

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
}
