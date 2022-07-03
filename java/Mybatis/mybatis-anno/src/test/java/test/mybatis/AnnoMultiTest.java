package test.mybatis;

import com.twhupup.mapper.OrderMapper;
import com.twhupup.entity.Order;
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
 * @Date: 2022/6/30 15:31
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public class AnnoMultiTest {
	private OrderMapper mapper;

	@Before
	public void before() throws IOException {
		InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");//输入核心配置类地址
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		//通过SqlSession工厂，获取sqlSession对象
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		mapper = sqlSession.getMapper(OrderMapper.class);
	}

	@Test
	public void testFindAll(){
		List<Order> orders = mapper.findAll();
		for (int i = 0; i < orders.size(); i++) {
			System.out.println(orders.get(i));
		}
	}

}
