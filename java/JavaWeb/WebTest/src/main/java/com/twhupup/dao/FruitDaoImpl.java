package com.twhupup.dao;

import com.twhupup.entity.Fruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FruitDaoImpl implements FruitDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void add(Fruit fruit) {
        String sql = "insert into t_fruit values(?,?,?)";
        Object[] args = {fruit.getName(),fruit.getPrice(),fruit.getNumber()};
        jdbcTemplate.update(sql,args);
    }
}
