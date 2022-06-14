package database.leetcode_31;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Project: Leetcode
 * @Package: database.leetcode_31
 * @Date: 2022/2/25 17:20
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public class test {
    @Test
    public void testSolution(){
        Solution solution = new Solution();
        int[] array = {1,2,3};
        solution.nextPermutation(array);
        System.out.println(Arrays.toString(array));
    }
}
