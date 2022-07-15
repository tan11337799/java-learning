package com.twhupup.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.twhupup.reggie.common.BaseContext;
import com.twhupup.reggie.common.R;
import com.twhupup.reggie.entity.AddressBook;
import com.twhupup.reggie.entity.User;
import com.twhupup.reggie.service.AddressBookService;
import com.twhupup.reggie.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Project: reggie_take_out
 * Package: com.twhupup.reggie.controller
 * Date: 2022/7/12 15:29
 * Author: Wenhao Tan
 * Version: 1.0
 * License: (C)2022,MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
@RestController
@Slf4j
@RequestMapping("/addressBook")
public class AddressBookController {
    @Autowired
    private AddressBookService addressBookService;

    @Autowired
    private UserService userService;

    /**
     * 添加地址功能
     * @param addressBook
     * @return
     */
    @Transactional
    @PostMapping
    public R<String> save(@RequestBody AddressBook addressBook){
        log.info("POST请求:添加地址到地址簿");
        //如果未添加过地址，则将第一个地址设为默认地址
        LambdaQueryWrapper<AddressBook> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AddressBook::getUserId,BaseContext.getCurrentId());
        int count = addressBookService.count(wrapper);
        if (count==0){
            addressBook.setIsDefault(1);
        }
        addressBook.setUserId(BaseContext.getCurrentId());
        addressBookService.save(addressBook);
        return R.success(null);
    }

    /**
     * 设置默认地址功能
     * @param addressBook
     * @return
     */
    @Transactional
    @PutMapping("/default")
    public R<AddressBook> setDefault(@RequestBody AddressBook addressBook){
        log.info("PUT请求:修改地址为默认地址");
        //这里使用LambdaUpdateWrapper，将地址簿该userId的所有地址的default字段都改为0（先去除地址可能存在的default状态）
        LambdaUpdateWrapper<AddressBook> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(AddressBook::getUserId,BaseContext.getCurrentId());
        wrapper.set(AddressBook::getIsDefault,0);
        //service的update方法只能传入LambdaUpdateWrapper对象
        addressBookService.update(wrapper);
        //将当前地址的default改为1
        addressBook.setIsDefault(1);
        addressBookService.updateById(addressBook);
        return R.success(addressBook);
    }

    /**
     * 查询id对应的地址
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<AddressBook> getById(@PathVariable Long id){
        AddressBook addressBook = addressBookService.getById(id);
        if(addressBook!=null){
            return R.success(addressBook);
        }else{
            return R.error("找不到该对象！");
        }
    }

    /**
     * 查询默认地址
     * @return
     */
    @GetMapping("/default")
    public R<AddressBook> getDefault() {
        LambdaQueryWrapper<AddressBook> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AddressBook::getUserId,BaseContext.getCurrentId());
        queryWrapper.eq(AddressBook::getIsDefault,1);

        AddressBook addressBook = addressBookService.getOne(queryWrapper);
        if(null==addressBook){
            return R.error("找不到该对象！");
        }
        else{
            return R.success(addressBook);
        }
    }

    @GetMapping("/list/{loginUserPhone}")
    public R<List<AddressBook>> list(@PathVariable("loginUserPhone") String phone){
        //获取当前登录用户的信息
        LambdaQueryWrapper<User> userWrapper = new LambdaQueryWrapper<>();
        userWrapper.eq(User::getPhone,phone);
        User user = userService.getOne(userWrapper);

        //根据userId查询其地址簿
        LambdaQueryWrapper<AddressBook> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(AddressBook::getUserId,user.getId());
        List<AddressBook> addressBookList = addressBookService.list(wrapper);

        return R.success(addressBookList);
    }

    @DeleteMapping
    public R<String> remove(@RequestParam Long id){
        log.info("Delete请求:删除地址");
        boolean flag = addressBookService.removeById(id);
        if(flag){
            return R.success(null);
        }
        return R.error("删除失败");
    }
}
