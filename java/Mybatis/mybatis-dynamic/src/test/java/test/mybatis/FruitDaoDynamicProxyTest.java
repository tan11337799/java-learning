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
 * @Project: mybatis-dynamic
 * @Package: com.twhupup.service
 * @Date: 2022/6/27 14:27
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public class FruitDaoDynamicProxyTest {
	@Test
	public void testSelectAll() throws IOException {
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		FruitMapper mapper = sqlSession.getMapper(FruitMapper.class);
		List<Fruit> all = mapper.findAll();
		System.out.println(all);
	}
	@Test
	public void testSelectOne() throws IOException {
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		FruitMapper mapper = sqlSession.getMapper(FruitMapper.class);
		Fruit one = mapper.findOne(5);
		System.out.println(one);
	}
}
