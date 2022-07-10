package com.twhupup.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.twhupup.reggie.entity.Category;

/**
 * Project: reggie_take_out
 * Package: com.twhupup.reggie.service
 * Date: 2022/7/10 12:20
 * Author: Wenhao Tan
 * Version: 1.0
 * License: (C)2022,MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public interface CategoryService extends IService<Category> {
    public void remove(Long id);
}
