package database.leetcode_5;

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
        Solution s = new Solution();
        String res = s.longestPalindrome("abecceba");
        System.out.println(res);
    }
    @Test
    public void testSolution_2() {
        Solution_2 s = new Solution_2();
        String res = s.longestPalindrome("abecceba");
        System.out.println(res);
    }
    @Test
    public void testSolution_3() {
        Solution_3 s = new Solution_3();
        String res = s.longestPalindrome("abecceba");
        System.out.println(res);
    }
}
