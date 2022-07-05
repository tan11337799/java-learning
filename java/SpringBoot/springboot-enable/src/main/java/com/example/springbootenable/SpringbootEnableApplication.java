package com.example.springbootenable;

import com.example.config.MyImportBeanDefinitionRegistrar;
import com.example.domain.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

/**
 * @Import提供四种用法：
 * ① 导入Bean
 * ② 导入配置类
 * ③ 导入 ImportSelector 实现类。一般用于加载配置文件中的类
 * ④ 导入 ImportBeanDefinitionRegistrar 实现类。
 */

@Import(MyImportBeanDefinitionRegistrar.class)
@SpringBootApplication
public class SpringbootEnableApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringbootEnableApplication.class, args);
        Object user = context.getBean(User.class);
        // Object role = context.getBean(Role.class);
        System.out.println(user);
        // System.out.println(role);
    }
}
