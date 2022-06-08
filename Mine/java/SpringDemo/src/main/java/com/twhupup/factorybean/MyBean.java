package com.twhupup.factorybean;

import com.twhupup.collectiontype.Course;
import org.springframework.beans.factory.FactoryBean;

/**
 * @Project: SpringTest
 * @Package: com.twhupup.factorybean
 * @Date: 2022/3/7 16:39
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */

//FactoryBean接口泛型的选择决定了getObject的返回值类型
public class MyBean implements FactoryBean<Course> {
    //返回bean实例对象
    @Override
    public Course getObject() throws Exception {
        Course course = new Course();
        course.setName("abc");
        return course;
    }

    //返回类型
    @Override
    public Class<?> getObjectType() {
        return null;
    }

    //判断是否是单例
    @Override
    public boolean isSingleton() {
        return true;
    }
}
