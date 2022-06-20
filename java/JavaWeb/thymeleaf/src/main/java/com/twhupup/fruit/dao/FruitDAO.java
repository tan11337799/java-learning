package com.twhupup.fruit.dao;

import com.twhupup.fruit.pojo.Fruit;

import java.util.List;

public interface FruitDAO {
    void add(Fruit fruit);
    void del(int id);
    void update(Fruit fruit);
    // Integer selectCount();
    Fruit getByFid(int id);
    List<Fruit> findAll();
}
