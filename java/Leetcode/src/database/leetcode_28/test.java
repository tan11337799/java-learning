package database.leetcode_28;

import org.junit.Test;

/**
 * @Project: Leetcode
 * @Package: database.leetcode_2
 * @Date: 2022/2/25 16:58
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public class test {
    @Test
    public void testSolution() {
        String s = "abccabc";
        String p = "cabc";
        Solution solution = new Solution();
        System.out.println(solution.strStr(s, p));
    }
    @Test
    public void testSolution_2() {
        String s = "aabcabc";
        String p = "aabc";
        Solution_2 solution = new Solution_2();
        System.out.println(solution.strStr(s, p));
    }
}
