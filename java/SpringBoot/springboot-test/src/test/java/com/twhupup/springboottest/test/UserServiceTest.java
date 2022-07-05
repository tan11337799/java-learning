package com.twhupup.springboottest.test;

import com.twhupup.springboottest.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Project: SpringBoot
 * @Package: com.twhupup.com.twhupup
 * @Date: 2022/7/3 14:22
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = "dev")
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @Test
    public void testAdd(){
        userService.add();
    }


}
