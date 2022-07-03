package basic.sort;

/**
 * @Project: Leetcode
 * @Package: basic.sort
 * @Date: 2022/6/30 14:49
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public class MergeSort {

    public int[] sortArray(int[] nums) {
        int[] tmp = new int[nums.length];
        mergeSort(nums, 0, nums.length - 1, tmp);
        return nums;
    }

    public void mergeSort(int[] nums, int left, int right, int[] tmp) {
        if (left >= right) return;
        int mid = (left + right) >> 1;

        mergeSort(nums, left, mid, tmp);
        mergeSort(nums, mid + 1, right, tmp);

        int i = left;
        int j = mid + 1;
        int cnt = 0;

        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                tmp[cnt++] = nums[i++];
            } else {
                tmp[cnt++] = nums[j++];
            }
        }

        while (i <= mid) {
            tmp[cnt++] = nums[i++];
        }
        while (j <= right) {
            tmp[cnt++] = nums[j++];
        }

        cnt = 0;
        while(left<=right){
            nums[left++] = tmp[cnt++];
        }
    }
}
