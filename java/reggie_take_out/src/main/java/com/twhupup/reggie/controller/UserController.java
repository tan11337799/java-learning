package com.twhupup.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.twhupup.reggie.common.R;
import com.twhupup.reggie.dto.UserDto;
import com.twhupup.reggie.entity.User;
import com.twhupup.reggie.service.UserService;
import com.twhupup.reggie.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Project: reggie_take_out
 * Package: com.twhupup.reggie.controller
 * Date: 2022/7/12 13:44
 * Author: Wenhao Tan
 * Version: 1.0
 * License: (C)2022,MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 发送验证码功能，存储验证码到会话中
     * @param user
     * @param session
     * @return
     */
    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session){
        log.info("POST请求:发送验证码");
        //获取手机号
        String phone = user.getPhone();
        if(StringUtils.isNotEmpty(phone)){
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            log.info("code={}",code);
            //将生成的验证码保存到session中
            session.setAttribute(phone,code);
            //完成短信服务API发送短信
            //...
            return R.success(null);
        }
        return R.error("未知错误，短信发送失败！");
    }

    /**
     * 用户登陆功能，判断验证码是否正确；向前端返回数据库user数据
     * @param userDto
     * @param session
     * @return
     */
    @PostMapping("/login")
    public R<User> login(@RequestBody UserDto userDto, HttpSession session){
        log.info("POST请求:用户登录");

        //获取手机号、验证码
        String phone = userDto.getPhone();
        String code = userDto.getCode();
        //获取session中保存的验证码，进行验证码比对
        Object sessionCode = session.getAttribute(phone);
        if(sessionCode!=null && sessionCode.equals((code))){
            //判断手机号是否是新用户，如果是完成自动注册
            LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
            userLambdaQueryWrapper.eq(User::getPhone,phone);
            User user = userService.getOne(userLambdaQueryWrapper);
            //如果为新用户，完成注册（插入手机号到数据库中）
            if(user==null){
                user = new User();
                user.setPhone(phone);
                user.setStatus(1);
                userService.save(user);
            }
            session.setAttribute("user",user.getId());
            return R.success(user);
        }
        return R.error("验证码错误，登陆失败！");
    }
}
