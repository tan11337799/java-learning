import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Project: reggie_take_out
 * Package: PACKAGE_NAME
 * Date: 2022/7/12 9:43
 * Author: Wenhao Tan
 * Version: 1.0
 * License: (C)2022,MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public class javaTest {
	@Test
	public void test(){
		List<A> tlist = new ArrayList<>();
		tlist.add(new A(1));
		tlist.add(new A(1));
		tlist.add(new A(1));
		tlist.stream().map((item)->{
			item.setA(2);
			return item;
		}).collect(Collectors.toList());
		System.out.println(tlist);

	}
}

class A{
	private int a = 10;

	public A(int a) {
		this.a = a;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	@Override
	public String toString() {
		return "A{" +
				"a=" + a +
				'}';
	}
}
