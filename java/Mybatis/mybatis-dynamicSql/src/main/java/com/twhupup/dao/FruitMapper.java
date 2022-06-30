package com.twhupup.dao;

import com.twhupup.entity.Fruit;

import java.io.IOException;
import java.util.List;

/**
 * @Project: mybatis-dynamic
 * @Package: com.twhupup.dao
 * @Date: 2022/6/27 14:14
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public interface FruitMapper {
    public List<Fruit> findByCondition(Fruit fruit) throws IOException;
    public List<Fruit> findByIds(List<Integer> ids) throws IOException;
}
