package database.leetcode.leetcode_28;

/**
 * @Project: Leetcode
 * @Package: database.leetcode_28
 * @Date: 2022/2/25 16:27
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 * 实现strStr()函数。
 *
 * 给你两个字符串haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回 -1 。
 * 说明：
 * 当needle是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * 对于本题而言，当needle是空字符串时我们应当返回 0 。这与 C 语言的strstr()以及 Java 的indexOf()定义相符。
 */

//KMP字符串匹配算法
public class Solution {
    public int strStr(String haystack, String needle) {
        char[] ss = haystack.toCharArray();
        char[] pp = needle.toCharArray();

        int s_length = ss.length;
        int p_length = pp.length;

        if(s_length<p_length) return -1;
        if(p_length==0) return 0;

        int[] next = new int[p_length];

        for(int i=1,j=0;i<p_length;i++){
            while(j>0 && pp[i]!=pp[j]){
                j = next[j-1];
            }
            if(pp[i]==pp[j]){
                j++;
            }
            next[i] = j;
        }
        for(int i=0,j=0;i<s_length;i++){
            while(j>0 && ss[i]!=pp[j]){
                j = next[j-1];
            }
            if(ss[i]==pp[j]){
                j++;
            }
            if(j==p_length){
                return i-p_length+1;
            }
        }
        return 0;
    }
}
