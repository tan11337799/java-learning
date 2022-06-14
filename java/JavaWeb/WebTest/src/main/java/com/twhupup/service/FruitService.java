package com.twhupup.service;

import com.twhupup.dao.FruitDao;
import com.twhupup.entity.Fruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class FruitService {
    @Autowired
    private FruitDao fruitDao;
    public void addFruit(Fruit fruit){
        fruitDao.add(fruit);
    }
}
