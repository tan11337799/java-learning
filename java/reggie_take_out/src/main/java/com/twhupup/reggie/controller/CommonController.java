package com.twhupup.reggie.controller;

import com.twhupup.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Project: reggie_take_out
 * Package: com.twhupup.reggie.controller
 * Date: 2022/7/10 16:17
 * Author: Wenhao Tan
 * Version: 1.0
 * License: (C)2022,MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController {
    @RequestMapping("/upload")
    public R<String> upload(MultipartFile file) {

        return null;
    }

}
