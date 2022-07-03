package com.twhupup.reggie.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.twhupup.reggie.entity.Employee;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Project: reggie_take_out
 * @Package: com.twhupup.reggie.mapper
 * @Date: 2022/7/1 10:01
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public interface EmployeeMapper extends BaseMapper<Employee> {
    @Override
    default int insert(Employee entity) {
        return 0;
    }

    @Override
    default int deleteById(Serializable id) {
        return 0;
    }

    @Override
    default int deleteByMap(Map<String, Object> columnMap) {
        return 0;
    }

    @Override
    default int delete(Wrapper<Employee> queryWrapper) {
        return 0;
    }

    @Override
    default int deleteBatchIds(Collection<? extends Serializable> idList) {
        return 0;
    }

    @Override
    default int updateById(Employee entity) {
        return 0;
    }

    @Override
    default int update(Employee entity, Wrapper<Employee> updateWrapper) {
        return 0;
    }

    @Override
    default Employee selectById(Serializable id) {
        return null;
    }

    @Override
    default List<Employee> selectBatchIds(Collection<? extends Serializable> idList) {
        return null;
    }

    @Override
    default List<Employee> selectByMap(Map<String, Object> columnMap) {
        return null;
    }

    @Override
    default Employee selectOne(Wrapper<Employee> queryWrapper) {
        return null;
    }

    @Override
    default Integer selectCount(Wrapper<Employee> queryWrapper) {
        return null;
    }

    @Override
    default List<Employee> selectList(Wrapper<Employee> queryWrapper) {
        return null;
    }

    @Override
    default List<Map<String, Object>> selectMaps(Wrapper<Employee> queryWrapper) {
        return null;
    }

    @Override
    default List<Object> selectObjs(Wrapper<Employee> queryWrapper) {
        return null;
    }

    @Override
    default <E extends IPage<Employee>> E selectPage(E page, Wrapper<Employee> queryWrapper) {
        return null;
    }

    @Override
    default <E extends IPage<Map<String, Object>>> E selectMapsPage(E page, Wrapper<Employee> queryWrapper) {
        return null;
    }
}
