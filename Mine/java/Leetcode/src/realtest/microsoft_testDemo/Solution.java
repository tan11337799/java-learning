package realtest.microsoft_testDemo;
import java.util.*;
/**
 * @Project: Leetcode
 * @Package: realtest
 * @Date: 2022/3/12 14:27
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 * This is a demo task.
 * Write a function:
 * class Solution { public int solution(int[] A); }
 * that, given an array A of N integers, returns the smallest positive integer (greater than 0) that does not occur in A.
 * For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.
 * Given A = [1, 2, 3], the function should return 4.
 * Given A = [−1, −3], the function should return 1.
 */
class Solution {
    public int solution(int[] A){
        Set<Integer> hashset = new HashSet<>();
        int max = 0;

        for (int i = 0; i < A.length; i++) {
            hashset.add(A[i]);
            max = Math.max(max,A[i]);
        }
        for (int i = 1; i < A.length; i++) {
            if(!hashset.contains(i)){
                return i;
            }
        }
        return max+1;
    }
}
