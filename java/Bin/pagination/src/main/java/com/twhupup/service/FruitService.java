package com.twhupup.service;

import com.twhupup.dao.FruitDAO;
import com.twhupup.entity.Fruit;
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
    public int getFruitCount(){
        return fruitDAO.selectCount();
    }
    public List<Fruit> findAllFruit(){
        return fruitDAO.findAll();
    }
    public List<Fruit> findAllFruitByPage(int pageNum){
        return fruitDAO.findAllByPage(pageNum);
    }
}
