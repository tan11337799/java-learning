package com.twhupup.fruit.dao;

import com.twhupup.fruit.pojo.Fruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FruitDAOImpl implements FruitDAO{
    //根据xml文件中的属性注入JdbcTemplate
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void add(Fruit fruit) {
        String sql = "insert into t_fruit values(?,?,?,?,?)";
        Object[] args = {fruit.getFid(),fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark()};
        jdbcTemplate.update(sql,args);
    }

    @Override
    public void del(int id) {
        String sql = "delete from t_fruit where fid=?";
        jdbcTemplate.update(sql,id);
    }

    @Override
    public void update(Fruit fruit) {
        String sql = "update t_fruit set fname=?,price=?,fcount=?,remark=? where fid=?";
        Object[] args = {fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark(),fruit.getFid()};
        jdbcTemplate.update(sql,args);
    }

    // @Override
    // public Integer selectCount() {
    //     String sql = "select count(*) from t_book";
    //     return jdbcTemplate.queryForObject(sql,Integer.class);
    // }

    @Override
    public Fruit getByFid(int id) {
        String sql = "select * from t_book where fid=?";
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<Fruit>(Fruit.class),id);
    }

    @Override
    public List<Fruit> findAll() {
        String sql = "select * from t_fruit";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Fruit>(Fruit.class));
    }

    // @Override
    // public void batchAddBook(List<Object[]> batchArgs) {
    //     String sql = "insert into t_book values(?,?,?)";
    //     int[] ints = jdbcTemplate.batchUpdate(sql, batchArgs);
    //     System.out.println(Arrays.toString(ints));
    // }
    //
    //
    // @Override
    // public void batchUpdBook(List<Object[]> batchArgs) {
    //     String sql = "update t_book set book_name=?,book_status=? where book_id=?";
    //     int[] ints = jdbcTemplate.batchUpdate(sql, batchArgs);
    //     System.out.println(Arrays.toString(ints));
    // }
    //
    // @Override
    // public void batchDelBook(List<Object[]> batchArgs) {
    //     String sql = "delete from t_book where book_id=?";
    //     int[] ints = jdbcTemplate.batchUpdate(sql, batchArgs);
    //     System.out.println(Arrays.toString(ints));
    // }
}
