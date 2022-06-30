package test.mybatis;

import com.twhupup.dao.OrderMapper;
import com.twhupup.dao.UserMapper;
import com.twhupup.entity.Order;
import com.twhupup.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
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
    //一对一关系：一个订单对应一个用户
    @Test
    public void testFindAll() throws IOException {
        //通过加载mybatis核心文件的输入流形式构建SqlSessionFactory对象
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");//输入核心配置类地址
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //通过SqlSession工厂，获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        // 设置分页参数
        List<Order> list = orderMapper.selectAll();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        sqlSession.commit();
        sqlSession.close();
    }
    //一对多关系：一个用户对应多个订单
    @Test
    public void testSelectAll() throws IOException {
        //通过加载mybatis核心文件的输入流形式构建SqlSessionFactory对象
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");//输入核心配置类地址
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //通过SqlSession工厂，获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        // 设置分页参数
        List<User> list = mapper.selectAll();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        sqlSession.commit();
        sqlSession.close();
    }
}
