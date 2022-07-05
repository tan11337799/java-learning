package database.bm.bm_42;

import java.util.Stack;

/**
 * Project: Leetcode
 * Package: database.bm.bm_42
 * Date: 2022/7/5 21:51
 * Author: Wenhao Tan
 * Version: 1.0
 * License: (C)2022,MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 * BM42 用两个栈实现队列c
 * 描述
 * 用两个栈来实现一个队列，使用n个元素来完成 n 次在队列尾部插入整数(push)和n次在队列头部删除整数(pop)的功能。 队列中的元素为int类型。保证操作合法，即保证pop操作时队列内已有元素。
 *
 * 数据范围： n\le1000n≤1000
 * 要求：存储n个元素的空间复杂度为 O(n)O(n) ，插入与删除的时间复杂度都是 O(1)O(1)c
 */
public class Solution {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        //每次只有s2为空时，再将s1中元素依次入s2栈中
        if(stack2.isEmpty()){
            while(!stack1.isEmpty()){
                int val = stack1.pop();
                stack2.push(val);
            }
        }
        //将s2栈顶元素移出并返回
        int res = stack2.pop();
        return res;
    }
}
