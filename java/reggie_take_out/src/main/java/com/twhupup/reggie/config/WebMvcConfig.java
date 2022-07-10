package com.twhupup.reggie.config;

import com.twhupup.reggie.common.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * @Project: reggie_take_out
 * @Package: com.twhupup.reggie.config
 * @Date: 2022/6/30 19:36
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
@Slf4j
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    /**
     * 设置资源映射
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("开始静态资源映射...");
        //addResourceHandler()表示的是浏览器访问的请求
        //addResourceLocations()表示的是跳转请求映射到哪里去
        registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
        registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front/");
    }

    /**
     * 扩展mvc框架的消息转换器
     * @param converters
     */
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        //创建消息转换器对象（将controller的返回结果，转换为相应的json）
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        //设置对象转换器，底层使用Jackson将java对象转换为json
        messageConverter.setObjectMapper(new JacksonObjectMapper());
        //将消息转换器对象追加到mvc框架的转换器中(0，表示将该转换器作为index=0优先使用)
        converters.add(0,messageConverter);
    }
}
