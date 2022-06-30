package test.mybatis;

import com.twhupup.entity.Fruit;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Project: mybatis-basic
 * @Package: test.mybatis
 * @Date: 2022/6/26 15:09
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public class FruitOperationTest {
    @Test
    public void selectAll() throws IOException {
        //加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        //获得sqlSession工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行sql语句
        List<Fruit> fruitList = sqlSession.selectList("fruitMapper.findAll");
        //打印结果
        System.out.println(fruitList);
        //释放资源
        sqlSession.close();
    }

    @Test
    public void selectOne() throws IOException {
        //加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        //获得sqlSession工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行sql语句
        Fruit fruit = sqlSession.selectOne("fruitMapper.findOne",3);
        //打印结果
        System.out.println(fruit);
        //释放资源
        sqlSession.close();
    }

    @Test
    public void delete() throws IOException {
        //加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        //获得sqlSession工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行sql语句
        sqlSession.delete("fruitMapper.delete",11);
        //mybatis执行更新操作  提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

    @Test
    public void update() throws IOException {
        Fruit fruit = new Fruit();
        fruit.setFid(14);
        fruit.setFname("樱桃");
        fruit.setPrice(15);
        fruit.setFcount(25);
        fruit.setRemark("yintao");
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.update("fruitMapper.update",fruit);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void add() throws IOException {
        Fruit fruit = new Fruit();
        fruit.setFname("苹果");
        fruit.setPrice(10);
        fruit.setFcount(20);
        fruit.setRemark("apple");
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.insert("fruitMapper.save",fruit);
        sqlSession.commit();
        sqlSession.close();
    }
}
