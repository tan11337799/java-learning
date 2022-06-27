package com.twhupup.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:jdbc.properties")
@ComponentScan(basePackages = "com.twhupup")
@EnableTransactionManagement
public class SpringConfig {
    @Value("${driver}")
    private String driver;
    @Value("${url}")
    private String url;
    @Value("${username}")
    private String username;
    @Value("${password}")
    private String password;


    //@Bean注解会根据返回值的名称进行对象的注入
    @Bean("dataSource")
    public DataSource getDruidDatasource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    //在这里IOC容器会自动找到dataSource对象，并注入到jdbcTemplate bean对象中
    @Bean("jdbcTemplate")
    public JdbcTemplate getJdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean("dataSourceTransactionManager")
    public DataSourceTransactionManager getDataSourceTransactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

}
