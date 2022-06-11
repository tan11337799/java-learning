package com.twhupup.service;

import com.twhupup.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserDao userDao;
    //转账
    public void accountMoney(){
        userDao.reduceMoney();
        userDao.addMoney();
    }
}
