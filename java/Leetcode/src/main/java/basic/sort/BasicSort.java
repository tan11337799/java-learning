package basic.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Project: Leetcode
 * @Package: basic.sort
 * @Date: 2022/6/30 0:14
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public class BasicSort {
    // 冒泡排序
    // 原理：
    // 它重复地走访过要排序的元素列，依次比较两个相邻的元素，如果顺序（如从大到小、首字母从Z到A）错误就把他们交换过来，每次将最大的元素送到数组的最右端。
    // 走访元素的工作是重复地进行，直到没有相邻元素需要交换，也就是说该元素列已经排序完成。

    public int[] bubbleSort(int[] arr) {
        Boolean flag = false;
        int length = arr.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    SortToolbox.swap(arr, j, j + 1);
                    flag = true;
                }
            }
            if(!flag){
                break;
            }
        }
        return arr;
    }

    // 插入排序
    // 原理：
    // 假设前面 n-1(其中 n>=2)个数已经是排好顺序的，现将第 n 个数插到前面已经排好的序列中，然后找到合适自己的位置，使得插入第n个数的这个序列也是排好顺序的。
    // 按照此法对所有元素进行插入，直到整个序列排为有序。
    public int[] insertSort(int[] arr){
        int length = arr.length;
        for (int i = 1; i < length; i++) {//i表示预插入的元素索引，一旦发现该元素比之前的某个元素小，则将其插入到指定的位置（类似向前的冒泡）
            for(int j=i; j>0; j--){//j用于查看第i个元素与前面元素的关系，如果出现逆序，则轮流交换位置
                if(arr[j]<arr[j-1]){
                    SortToolbox.swap(arr,j,j-1);
                }
            }
        }
        return arr;
    }

    // 选择排序
    // 原理：
    // 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置。
    // 然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
    // 以此类推，直到所有元素均排序完毕。
    public int[] selectSort(int[] arr){
        int length = arr.length;

        for(int i=0;i<length;i++){//i表示需要找到的第i个小的数的位置
            for(int j=i+1;j<length;j++){//j表示从i往后找最小的过程，找到则放到第i个位置
                if(arr[i]>arr[j]){
                    SortToolbox.swap(arr, i,j);
                }
            }
        }
        return arr;
    }

    @Test
    public void test() {
        System.out.println(Arrays.toString(selectSort(new int[]{3, 4, 5, 1})));
    }

}
