package com.twhupup.fruit.service;

import com.twhupup.fruit.dao.FruitDAO;
import com.twhupup.fruit.pojo.Fruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class FruitService {
    @Autowired
    private FruitDAO fruitDAO;
    public void addFruit(Fruit fruit){
        fruitDAO.add(fruit);
    }
    public void delFruit(int id){
        fruitDAO.del(id);
    }
    public void updateFruit(Fruit fruit){
        fruitDAO.update(fruit);
    }
    public Fruit getFruitByFid(int id){
        return fruitDAO.getByFid(id);
    }
    public List<Fruit> findAllFruit(){
        return fruitDAO.findAll();
    }
}
