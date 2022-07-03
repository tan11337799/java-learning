package basic.sort;

import java.util.Random;

class QuickSort {
    public int[] sortArray(int[] nums) {
        return randomizeQuicksort(nums, 0, nums.length - 1);
    }

    //递归函数，每次找到分割点，将数组一分为二递归
    public int[] randomizeQuicksort(int[] nums, int left, int right) {
        //递归条件，一直递归到左右指针重合
        if (left < right) {
            //进行一趟排序并返回分割点(即枢轴位置)
            int partition = randomizePartition(nums, left, right);
            //递归每一趟下来分割得到的两数组
            randomizeQuicksort(nums, left, partition - 1);
            randomizeQuicksort(nums, partition + 1, right);
        }
        return nums;
    }

    //1.随机化选择枢轴
    //2.以选择的枢轴为基准，小值放左，最后将枢轴放中间，此时大值默认均在枢轴右边
    //3.返回当前枢轴位置，作为递归函数的分割点
    public int randomizePartition(int[] nums, int left, int right) {
        //随机化选出枢轴位置索引
        int pos = new Random().nextInt(right - left) + left;
        //将枢轴放于右边界
        swap(nums, pos, right);
        //记录枢轴值
        int pivot = nums[right];
        //partition标记了第一个比pivot大的元素的位置
        int partition = left;
        //规定left和right边界，真正的指针移动仅仅是partition
        for (int i = left; i < right; i++) {
            //小了要交换是因为向后遍历时，要将后面小的数字移到partition的位置（如果第一个元素小于pivot则只移动partition)
            if (nums[i] <= pivot) {
                swap(nums, i, partition);
                //由于交换后原来partition位置是交换过来的比pivot小的数，根据定义应该将该指针向后移动一格
                ++partition;
            }
        }
        //将第一个比枢轴大的值放于最右端，枢轴放中间
        swap(nums, partition, right);
        //返回分割点
        return partition;
    }

    //无脑将两元素交换的函数
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
