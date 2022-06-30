package database.leetcode.leetcode_31;

/**
 * @Project: Leetcode
 * @Package: database.leetcode_31
 * @Date: 2022/2/25 17:13
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 * 31. 下一个排列
 * 整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。
 * 例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
 * 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。
 * 例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
 * 类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
 * 而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
 * 给你一个整数数组 nums ，找出 nums 的下一个排列。
 * 必须 原地 修改，只允许使用额外常数空间。
 */
public class Solution {
    public void nextPermutation(int[] nums) {
        //从右往左找到第一个不是倒序排列的元素
        int i = nums.length-2;
        while(i>=0 && nums[i]>=nums[i+1]){
            i--;
        }
        //找到i右边只比i大一点的元素
        if(i>=0) {
            int j = nums.length-1;
            while (j > i && nums[j]<=nums[i]){
                j--;
            }
            swap(nums,i,j);
        }
        reverse(nums,i+1);
    }

    public void swap(int[] nums,int a,int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public void reverse(int[] nums,int start){
        int left = start;
        int right = nums.length-1;
        while(left<right){
            swap(nums,left,right);
            left++;
            right--;

        }
    }
}
