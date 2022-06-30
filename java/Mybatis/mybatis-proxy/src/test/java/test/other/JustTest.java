package test.other;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Project: mybatis-basic
 * @Package: test.other
 * @Date: 2022/6/27 11:29
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public class JustTest {
	@Test
	public void testReflection() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		Class<?> fruitDaoClass = Class.forName("com.twhupup.dao.fruitDao");
		Object obj = fruitDaoClass.newInstance();
		Method method = fruitDaoClass.getDeclaredMethod("print",int.class);
		method.invoke(obj,10);

	}
}
