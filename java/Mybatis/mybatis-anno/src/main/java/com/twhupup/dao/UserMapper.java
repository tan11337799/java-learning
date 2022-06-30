package com.twhupup.dao;

import com.twhupup.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Project: mybatis-dynamic
 * @Package: com.twhupup.dao
 * @Date: 2022/6/27 14:14
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public interface  UserMapper {
    @Insert("insert into user values(#{id},#{username},#{password},#{birthday})")
    public void save(User user);
    @Update("update user set username=#{username},password=#{password},birthday=#{birthday} where id=#{id}")
    public void update(User user);
    @Delete("delete from user where id=#{id}")
    public void delete(int id);
    @Select("select * from user where id=#{id}")
    public User findById(int id);
    @Select("select * from user")
    public List<User> findAll();
}
