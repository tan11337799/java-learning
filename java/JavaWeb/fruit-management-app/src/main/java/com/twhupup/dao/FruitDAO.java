package com.twhupup.dao;

import com.twhupup.entity.Fruit;

import java.util.List;

public interface FruitDAO {
    void add(Fruit fruit);
    void del(int id);
    void update(Fruit fruit);
    Integer selectCount();
    public Integer selectCount(String keyword);
    Fruit getByFid(int id);
    List<Fruit> findAll();
    List<Fruit> findAll(int pageNum);
    List<Fruit> findAll(String keyword, int pageNum);
}
