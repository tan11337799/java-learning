package database.bm.bm_51;
import java.util.*;

/**
 * @Project: Leetcode
 * @Package: database.bm_51
 * @Date: 2022/7/3 23:49
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 * BM51 数组中出现次数超过一半的数字
 * 描述
 * 给一个长度为 n 的数组，数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 例如输入一个长度为9的数组[1,2,3,2,2,2,5,4,2]。由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。
 *
 * 数据范围：n \le 50000n≤50000，数组中元素的值 0 \le val \le 100000≤val≤10000
 * 要求：空间复杂度：O(1)O(1)，时间复杂度 O(n)O(n)
 * 输入描述：
 * 保证数组输入非空，且保证有解
 */
public class Solution {
    public int MoreThanHalfNum_Solution(int [] array) {
        int length = array.length;
        if(length==1){
            return array[0];
        }
        int threshold = length/2;
        HashMap<Integer,Integer> hm = new HashMap<>();
        for(int i=0;i<length;i++){
            if(!hm.containsKey(array[i])){
                hm.put(array[i],1);
            }else{
                hm.put(array[i],hm.get(array[i])+1);
                if(hm.get(array[i])>threshold){
                    return array[i];
                }
            }
        }
        throw new IllegalArgumentException("No answer!");
    }
}