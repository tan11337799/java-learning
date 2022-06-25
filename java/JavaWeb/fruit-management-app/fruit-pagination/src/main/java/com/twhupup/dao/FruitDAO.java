package com.twhupup.dao;

import com.twhupup.entity.Fruit;

import java.util.List;

public interface FruitDAO {
    void add(Fruit fruit);
    void del(int id);
    void update(Fruit fruit);
    Integer selectCount();
    Fruit getByFid(int id);
    List<Fruit> findAll();
    List<Fruit> findAllByPage(int pageNum);
}
