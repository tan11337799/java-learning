package test.jdbc;

import com.twhupup.entity.Fruit;
import org.junit.Test;

import java.sql.*;

/**
 * @Project: mybatis-basic
 * @Package: jdbc.test
 * @Date: 2022/6/26 14:39
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public class select {
	//位于持久层dao
	@Test
	public void test() throws ClassNotFoundException, SQLException {
		//注册驱动
		Class.forName("com.mysql.jdbc.Driver");
		//根据url username password获取连接
		Connection conn = DriverManager.getConnection("jdbc:mysql:///fruit_db", "root", "root");
		//添加执行sql语句
		PreparedStatement statement = conn.prepareStatement("select * from t_fruit");
		//获取sql语句执行结果ResultSet不会直接返回给业务层，因此在程序开发之中，层与层之间的数据传递一般通过实体进行传递
		ResultSet resultSet = statement.executeQuery();
		//遍历resultSet
		while(resultSet.next()){
			Fruit fruit = new Fruit();
			fruit.setFid(resultSet.getInt("fid"));
			fruit.setFname(resultSet.getString("fname"));
			fruit.setPrice(resultSet.getInt("price"));
			fruit.setFcount(resultSet.getInt("fcount"));
			fruit.setRemark(resultSet.getString("remark"));
		}
		//关闭连接
		resultSet.close();
		statement.close();
		conn.close();
	}


}
