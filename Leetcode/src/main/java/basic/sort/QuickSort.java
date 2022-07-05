package basic.sort;

import java.util.Random;

/**
 * @Project: Leetcode
 * @Package: basic.sort
 * @Date: 2022/7/4 10:03
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public class QuickSort {
    public int[] sortArray(int[] nums) {
        int length = nums.length;
        randomQuicksort(nums, 0, length - 1);
        return nums;
    }

    //递归函数，每次找到分割点，将数组一分为二递归
    public void randomQuicksort(int[] nums, int left, int right) {
        //递归条件，一直递归到左右指针重合
        if (left < right) {
            //进行一趟排序并返回分割点(即枢轴位置)
            int partition = randomPartition(nums, left, right);
            //递归每一趟下来分割得到的两数组
            randomQuicksort(nums, left, partition - 1);
            randomQuicksort(nums, partition + 1, right);
        }
    }

    public int randomPartition(int[] nums, int left, int right) {
        int random = new Random().nextInt(right - left + 1) + left;
        //将随机数和最左侧元素交换位置
        swap(nums, left, random);
        //选择最左侧元素作为基准值
        int pivot = nums[left];
        //lt指针用于表示最后一个小于pivot的元素索引，这样最后将pivot所在值与最后一个小于pivot元素交换，则可以实现左小右大
        int lt = left;
        //需要保证从第二个元素开始，因为第一个元素需要保留用于最终交换
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] < pivot) {
                lt++;
                swap(nums, i, lt);
            }
        }
        swap(nums, left, lt);
        return lt;
    }

    public void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
