package com.twhupup.dao;

import com.twhupup.entity.Fruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class FruitDaoImpl implements FruitDao{
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(Fruit fruit) {
        String sql = "insert into t_fruit values(?,?,?,?)";
        Object[] args = {fruit.getName(),fruit.getPrice(),fruit.getNumber(),fruit.getDes()};
        return jdbcTemplate.update(sql,args);
    }
}
