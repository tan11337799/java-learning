package test.mybatis;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.twhupup.dao.UserMapper;
import com.twhupup.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @Project: mybatis-dynamicSql
 * @Package: test.mybatis
 * @Date: 2022/6/27 15:18
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public class MapperTest {
    @Test
    public void testAdd() throws IOException {
        //通过加载mybatis核心文件的输入流形式构建SqlSessionFactory对象
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");//输入核心配置类地址
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //通过SqlSession工厂，获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = new User();
        user.setUsername("ac");
        user.setPassword("ac");
        user.setBirthday(new Date());
        userMapper.save(user);

        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void testFindById() throws IOException {
        //通过加载mybatis核心文件的输入流形式构建SqlSessionFactory对象
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");//输入核心配置类地址
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //通过SqlSession工厂，获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = userMapper.selectById(4);
        System.out.println(user);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testFindAll() throws IOException {
        //通过加载mybatis核心文件的输入流形式构建SqlSessionFactory对象
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");//输入核心配置类地址
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //通过SqlSession工厂，获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 设置分页参数
        PageHelper.startPage(1,3);
        List<User> list = userMapper.selectAll();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        PageInfo<User> userPageInfo = new PageInfo<>(list);
        //获取当前页
        System.out.println(userPageInfo.getPageNum());
        //获取每页显示条数
        System.out.println(userPageInfo.getPageSize());
        //获取总条数
        System.out.println(userPageInfo.getTotal());
        //获取总页数
        System.out.println(userPageInfo.getPages());

        sqlSession.commit();
        sqlSession.close();
    }
}
