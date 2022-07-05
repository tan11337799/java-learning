package database.bm.bm_50;

import java.util.*;

/**
 * @Project: Leetcode
 * @Package: database.bm.bm_50
 * @Date: 2022/7/3 23:16
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 * BM50 两数之和
 *
 * 描述
 * 给出一个整型数组 numbers 和一个目标值 target，请在数组中找出两个加起来等于目标值的数的下标，返回的下标按升序排列。
 * （注：返回的数组下标从1开始算起，保证target一定可以由数组里面2个数字相加得到）
 * 要求：空间复杂度 O(n)O(n)，时间复杂度 O(nlogn)O(nlogn)
 */


public class Solution {
	/**
	 *
	 * @param numbers int整型一维数组
	 * @param target int整型
	 * @return int整型一维数组
	 */
	public int[] twoSum(int[] numbers, int target) {
		// write code here
		HashMap<Integer,Integer> hm = new HashMap<>();
		for(int i=0;i<numbers.length;i++){
			int diff = target-numbers[i];
			if(!hm.containsKey(diff)){
				hm.put(numbers[i],i);
			}else{
				return new int[]{hm.get(diff)+1,i+1};
			}
		}
		throw new IllegalArgumentException("No solution");
	}
}