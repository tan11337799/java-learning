package database.bm.bm_63;

/**
 * @Project: Leetcode
 * @Package: database.bm.bm_63
 * @Date: 2022/7/3 23:40
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 * BM63 跳台阶
 * 描述
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个 n 级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 *
 * 数据范围：1 \leq n \leq 401≤n≤40
 * 要求：时间复杂度：O(n)O(n) ，空间复杂度： O(1)O(1)
 */
//动态规划
public class Solution {
	//空间复杂度为o(n)
	public int jumpFloor(int target) {
		if(target==1) return 1;
		int[] dp = new int[target+1];//dp[i]表示跳到第i个台阶有dp[i]种方法
		dp[0] = 1;
		dp[1] = 1;
		for(int i=2;i<=target;i++){
			dp[i] = dp[i-1]+dp[i-2];
		}
		return dp[target];
	}
	//空间复杂度为1
	public int jumpFloor_2(int target) {
		if(target==1) return 1;
		int a = 1;
		int b = 1;
		int c = 0;
		for(int i=2;i<=target;i++){
			c = a+b;
			a = b;
			b = c;
		}
		return c;
	}
}