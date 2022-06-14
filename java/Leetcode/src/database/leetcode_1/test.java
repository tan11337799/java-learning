package database.leetcode_1;

import org.junit.Test;

/**
 * @Project: Leetcode
 * @Package: database.leetcode_1
 * @Date: 2022/2/25 16:57
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public class test {
    @Test
    public void testSolution() {
        Solution solution = new Solution();
        int[] result = solution.twoSum(new int[]{1,2,3},4);
        for (int j : result) {
            System.out.print(j);
            System.out.print(" ");
        }
    }
}
