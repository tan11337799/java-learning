package com.twhupup.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.twhupup.reggie.common.R;
import com.twhupup.reggie.entity.Employee;
import com.twhupup.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
     * @param session
     * @param employee
     * @return
     */
    @PostMapping("/login")
    public R<Employee> login(@RequestBody Employee employee, HttpSession session){
        //1. 将页面提交密码进行md5加密处理
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        //2. 根据页面提交的用户名查询数据库，查询该用户名在数据库中对应的实体对象
        // 由于username是唯一的，因此可以通过username找到唯一的emp对象
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);
        //3.如果没有查询到结果则返回登陆失败结果
        if(emp==null){
            return R.error("登陆失败，该用户不存在!");
        }
        //4.比对密码，如果不一致则返回登陆失败结果
        if(!emp.getPassword().equals(password)){
            return R.error("密码错误!");
        }
        //5.查看员工状态，如果为已禁用状态，则返回员工已被禁用的结果
        if(emp.getStatus()==0){
            return R.error("该账号已被禁用！");
        }
        //6.登陆成功，将员工的id存入session，返回登陆成功的结果(将查询到的emp对象传给前端)
        session.setAttribute("employee",emp.getId());
        return R.success(emp);
    }

    /**
     * 员工退出功能
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("employee");
        return R.success("退出成功！");
    }

    /**
     * 新增员工
     * @param employee
     * @return
     */
    //由于请求地址为/employee与类对应的地址一致，因此不需要配置子路径
    @PostMapping
    public R<String> save(@RequestBody Employee employee){
        // log.info("新增员工，员工信息为：{}",employee.toString());
        //1.设置用户初始密码，使用MD5加密;设置创建时间/更新时间；设置创建用户/更新用户
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        //以下字段通过元对象处理器MyMetaObjectHandler自动填充
        // employee.setCreateTime(LocalDateTime.now());
        // employee.setUpdateTime(LocalDateTime.now());
        // Long empId = (Long) request.getSession().getAttribute("employee");
        // employee.setCreateUser(empId);
        // employee.setUpdateUser(empId);

        employeeService.save(employee);
        return R.success(null);
    }

    /**
     * 员工信息分页查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page<Employee>> page(int page,int pageSize,String name){
        log.info("进行员工分页查询：page={},pageSize={},name={}",page,pageSize,name);
        //构造分页构造器
        Page<Employee> pageInfo = new Page<>(page, pageSize);
        //构造条件构造器用于分页的条件查询
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(name),Employee::getName,name)
                .orderByDesc(Employee::getUpdateTime);
        employeeService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }

    /**
     * 根据id修改员工信息
     * @param employee
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody Employee employee){
        log.info("需要修改的用户为：{}",employee.toString());
        employeeService.updateById(employee);
        return R.success(null);
    }

    /**
     * 根据id查询用户数据，用于修改用户信息时的回写
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long id){
        log.info("根据id查询员工信息...");
        Employee employee = employeeService.getById(id);
        if(employee!=null){
            return R.success(employee);
        }
        return R.error("没有查询到对应信息！");
    }
}
