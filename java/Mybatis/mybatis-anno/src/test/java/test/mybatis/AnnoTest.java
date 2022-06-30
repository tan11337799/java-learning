package test.mybatis;

import com.twhupup.dao.UserMapper;
import com.twhupup.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Project: mybatis-anno
 * @Package: test.mybatis
 * @Date: 2022/6/29 19:33
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public class AnnoTest {
    private UserMapper mapper;

    @Before
    public void before() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");//输入核心配置类地址
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //通过SqlSession工厂，获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        mapper = sqlSession.getMapper(UserMapper.class);
    }

    @Test
    public void testSave(){
        User user = new User();
        user.setUsername("tom");
        user.setPassword("abc");
        mapper.save(user);
    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(10);
        user.setUsername("tom");
        user.setPassword("abd");
        mapper.update(user);
    }

    @Test
    public void testDelete(){

        mapper.delete(9);
    }

    @Test
    public void testFindById(){

        User user = mapper.findById(2);
        System.out.println(user);
    }

    @Test
    public void testFindAll(){
        List<User> users = mapper.findAll();
        for (int i = 0; i < users.size(); i++) {
            System.out.println(users.get(i));
        }
    }

}
