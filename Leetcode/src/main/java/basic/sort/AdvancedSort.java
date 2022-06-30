package basic.sort;

/**
 * @Project: Leetcode
 * @Package: basic.sort
 * @Date: 2022/6/30 0:01
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public class AdvancedSort {
    public int[] quickSort(int[] nums,int l, int r){
        int left = 0;
        int right = nums.length-1;
        int val = nums[left];
        while(left<right){
            while(left<right && nums[right]>=val){
                right--;
            }
            SortToolbox.swap(nums,right,left);
            while(left<right && nums[left]<=val){
                left++;
            }
            SortToolbox.swap(nums,right,left);
        }
        nums[left] = val;

        quickSort(nums,l,left);
        quickSort(nums,right+1,r);
        return nums;
    }
}
