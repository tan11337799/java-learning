package database.bm.bm_64;

/**
 * Project: Leetcode
 * Package: database.bm.bm_64
 * Date: 2022/7/5 19:57
 * Author: Wenhao Tan
 * Version: 1.0
 * License: (C)2022,MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 *
 * BM64 最小花费爬楼梯
 * 描述
 * 给定一个整数数组 cost \cost  ，其中 cost[i]\cost[i]  是从楼梯第i \i 个台阶向上爬需要支付的费用，下标从0开始。一旦你支付此费用，即可选择向上爬一个或者两个台阶。
 *
 * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
 *
 * 请你计算并返回达到楼梯顶部的最低花费。
 *
 */
public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param cost int整型一维数组
     * @return int整型
     */
    public int minCostClimbingStairs (int[] cost) {
        // write code here
        int[] dp = new int[cost.length+1]; //表示爬到第i层的最低花费
        dp[0] = 0;
        dp[1] = 0;
        for(int i=2;i<=cost.length;i++){
            dp[i] = Math.min(dp[i-1]+cost[i-1],dp[i-2]+cost[i-2]);
        }
        return dp[cost.length];
    }
}