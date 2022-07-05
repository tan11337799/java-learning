package database.bm.bm_65;

/**
 * Project: Leetcode
 * Package: database.bm.bm_65
 * Date: 2022/7/5 20:37
 * Author: Wenhao Tan
 * Version: 1.0
 * License: (C)2022,MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 * BM65 最长公共子序列(二)
 * 描述
 * 给定两个字符串str1和str2，输出两个字符串的最长公共子序列。如果最长公共子序列为空，则返回"-1"。目前给出的数据，仅仅会存在一个最长的公共子序列
 * 数据范围：0 \le |str1|,|str2| \le 20000≤∣str1∣,∣str2∣≤2000
 * 要求：空间复杂度 O(n^2) 时间复杂度 O(n^2)
 */
public class Solution {
    public String LCS (String s1, String s2) {
        int length1 = s1.length();
        int length2 = s2.length();
        //dp[0][j]和dp[i][0]用于存储str1和str2为空字符串时的情况
        //dp[i][j]表示str1到i-1为止和str2从j-1为止的最长公共子序列
        String[][] dp = new String[length1+1][length2+1];

        for(int i=0;i<=length1;i++){
            for(int j=0;j<=length2;j++){
                if(i==0 || j==0) dp[i][j]="";
                else if(s1.charAt(i-1)==s2.charAt(j-1)) {
                    dp[i][j] = dp[i - 1][j - 1] + s1.charAt(i - 1);
                }
                else{
                    dp[i][j] = dp[i-1][j].length()>dp[i][j-1].length()?
                            dp[i-1][j]:dp[i][j-1];
                }
            }
        }
        if(dp[length1][length2].equals("")) return "-1";
        return dp[length1][length2];
    }

}
