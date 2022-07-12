package com.twhupup.reggie.controller;

import com.twhupup.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

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
    @Value("${reggie.path}")
    private String basePath;

    /**
     * 处理图片上传功能
     * @param file
     * @return
     */
    @RequestMapping("/upload")
    public R<String> upload(MultipartFile file) {
        log.info("图片上传");

        //获取原始文件名
        String originFileName = file.getOriginalFilename();
        //s.substring(index)将截取index索引到字符串结尾的子串，获取图片的后缀名
        String suffix = originFileName.substring(originFileName.lastIndexOf("."));

        //为了防止出现重名，我们使用uuid作为文件名
        String fileName = UUID.randomUUID().toString()+suffix;

        //创建目录
        File dir = new File(basePath);
        if(!dir.exists()){
            dir.mkdirs();
        }

        try {
            file.transferTo(new File(basePath+fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return R.success(fileName);
    }

    /**
     * 文件下载
     * @param name
     */
    //response用于获取输出流对象
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response){
        log.info("图片下载");
        try {
            //通过文件路径创建输入流对象；通过输入流获取文件数据
            FileInputStream fileInputStream = new FileInputStream(basePath+name);
            //根据response获取输出流对象；通过输出流将文件写回浏览器，在浏览器展示图片
            ServletOutputStream outputStream = response.getOutputStream();
            response.setContentType("image/jpeg");
            int len = 0;
            byte[] bytes = new byte[1024];
            while( (len=fileInputStream.read(bytes))!=-1 ){
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }
            fileInputStream.close();
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
