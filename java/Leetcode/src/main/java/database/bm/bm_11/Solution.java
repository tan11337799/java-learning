package database.bm.bm_11;

import basic.linkedlist.ListNode;

/**
 * @Project: Leetcode
 * @Package: database.bm
 * @Date: 2022/6/27 19:35
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 *
 * BM11 链表相加(二)
 * 描述
 * 假设链表中每一个节点的值都在 0 - 9 之间，那么链表整体就可以代表一个整数。
 * 给定两个这种链表，请生成代表两个整数相加值的结果链表。
 * 数据范围：0 \le n,m \le 10000000≤n,m≤1000000，链表任意值 0 \le val \le 90≤val≤9
 * 要求：空间复杂度 O(n)O(n)，时间复杂度 O(n)O(n)
 *
 * 例如：链表 1 为 9->3->7，链表 2 为 6->3，最后生成新的结果链表为 1->0->0->0。
 */

/*
 * public class ListNode {
 *   int val;
 *   ListNode next = null;
 * }
 */

public class Solution {
    /**
     *
     * @param head1 ListNode类
     * @param head2 ListNode类
     * @return ListNode类
     */
    public ListNode addInList (ListNode head1, ListNode head2) {
        // write code here
        if(head1==null){
            return head1;
        }
        if(head2==null){
            return head2;
        }
        ListNode l1 = reverse(head1);
        ListNode l2 = reverse(head2);
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        int c = 0;
        while(l1!=null || l2!=null || c!=0){
            int sum = (l1==null?0:l1.val)+(l2==null?0:l2.val)+c;
            cur.next = new ListNode(sum%10);
            c = sum/10;
            cur = cur.next;
            if(l1!=null){
                l1=l1.next;
            }
            if(l2!=null){
                l2=l2.next;
            }
        }
        ListNode result = dummy.next;
        return reverse(result);
    }



    public ListNode reverse(ListNode node){
        if(node==null) return node;
        ListNode cur = node;
        ListNode pre = null;
        while(cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}