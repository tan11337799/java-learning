package com.twhupup.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.twhupup.reggie.common.R;
import com.twhupup.reggie.entity.Employee;
import com.twhupup.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Project: reggie_take_out
 * @Package: com.twhupup.reggie.controller
 * @Date: 2022/7/2 8:52
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */

@Slf4j
@RestController //@RestController相当于@RequestBody+@Controller，定义该类为Controller类，并接收前端发送的请求
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * 员工登陆功能
     * 这里Employee的形参上添加@RequestBody注解，表示获取
     * @param request
     * @param employee
     * @return
     */
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){
        //1. 将页面提交密码进行md5加密处理
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        //2. 根据页面提交的用户名查询数据库，查询该用户名在数据库中对应的实体对象
        String username = employee.getUsername();
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);
        //3.如果没有查询到结果则返回登陆失败结果
        if(emp==null){
            return R.error("登陆失败!");
        }
        //4.比对密码，如果不一致则返回登陆失败结果
        if(!emp.getPassword().equals(password)){
            return R.error("登陆失败!");
        }
        //5.查看员工状态，如果为已禁用状态，则返回员工已被禁用的结果
        if(emp.getStatus()==0){
            return R.error("该账号已被禁用！");
        }
        //6.登陆成功，将员工的id存入session，返回登陆成功的结果
        request.getSession().setAttribute("employee",emp.getId());
        return R.success(emp);
    }

    /**
     * 员工退出功能
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){

        return null;
    }

}
