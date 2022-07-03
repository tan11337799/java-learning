package com.twhupup.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.twhupup.reggie.entity.Employee;
import com.twhupup.reggie.mapper.EmployeeMapper;
import com.twhupup.reggie.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * @Project: reggie_take_out
 * @Package: com.twhupup.reggie.service.impl
 * @Date: 2022/7/1 10:06
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService{

}
