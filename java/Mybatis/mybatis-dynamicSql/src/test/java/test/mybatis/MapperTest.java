package test.mybatis;

import com.twhupup.dao.FruitMapper;
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
 * @Project: mybatis-dynamicSql
 * @Package: test.mybatis
 * @Date: 2022/6/27 15:18
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public class MapperTest {
    @Test
    public void testCondition() throws IOException {
        //加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        //获得sqlSession工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取映射对象mapper
        FruitMapper mapper = sqlSession.getMapper(FruitMapper.class);
        //模拟条件对象
        Fruit condition = new Fruit();
        // condition.setFid(1);
        // condition.setFname("测试水果");
        condition.setPrice(15);
        // condition.setFcount(50);
        // condition.setRemark("测试评价");
        List<Fruit> byCondition = mapper.findByCondition(condition);
        System.out.println(byCondition);
        //释放资源
        sqlSession.close();
    }

    @Test
    public void testIf() throws IOException {
        //加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        //获得sqlSession工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取映射对象mapper
        FruitMapper mapper = sqlSession.getMapper(FruitMapper.class);

        //释放资源
        sqlSession.close();
    }
}
