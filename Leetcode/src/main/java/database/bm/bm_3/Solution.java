package database.bm.bm_3;

import basic.linkedlist.ListNode;
import basic.linkedlist.LLToolbox;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Project: Leetcode
 * @Package: database.bm.bm_3
 * @Date: 2022/6/27 22:52
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 *
 * BM3 链表中的节点每k个一组翻转
 *
 * 描述
 * 将给出的链表中的节点每 k 个一组翻转，返回翻转后的链表
 * 如果链表中的节点数不是 k 的倍数，将最后剩下的节点保持原样
 * 你不能更改节点中的值，只能更改节点本身。
 *
 * 数据范围： \ 0 \le n \le 2000 0≤n≤2000 ， 1 \le k \le 20001≤k≤2000 ，链表中每个元素都满足 0 \le val \le 10000≤val≤1000
 * 要求空间复杂度 O(1)O(1)，时间复杂度 O(n)O(n)
 * 例如：
 * 给定的链表是 1\to2\to3\to4\to51→2→3→4→5
 * 对于 k = 2k=2 , 你应该返回 2\to 1\to 4\to 3\to 52→1→4→3→5
 * 对于 k = 3k=3 , 你应该返回 3\to2 \to1 \to 4\to 53→2→1→4→5
 */
public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k < 2) return head;
        LLToolbox t = new LLToolbox();
        ListNode res = new ListNode(0);
        ListNode now = res;
        Deque<ListNode> stack = new LinkedList<>();
        int cnt = 0;
        do{
            //每次将k个元素放入栈中
            for (int i = 0; i < k; i++) {
                stack.push(head);
                head = head.next;
                cnt++;
                if (head == null) break;
            }

            if (cnt == k) {
                while (!stack.isEmpty()) {
                    now.next = stack.pop();
                    now = now.next;
                }
            }

            cnt = 0;
        }while(head!=null);

        ListNode end = null;
        while(!stack.isEmpty()){
            end = stack.pop();
            t.printLinkedList(end);
        }

        now.next = end;
        return res.next;
    }

    // @Test
    public void test(){
        LLToolbox t = new LLToolbox();
        ListNode input = t.createLinkedList(new int[]{1,2,3,4,5,6});
        t.printLinkedList(input);
        ListNode res = reverseKGroup(input,4);
        t.printLinkedList(res);
    }

}
