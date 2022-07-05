package database.bm.bm_12;

import basic.linkedlist.ListNode;

/**
 * Project: Leetcode
 * Package: database.bm.bm_12
 * Date: 2022/7/5 21:32
 * Author: Wenhao Tan
 * Version: 1.0
 * License: (C)2022,MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 * BM12 单链表的排序
 * 描述
 * 给定一个节点数为n的无序单链表，对其按升序排序。
 *
 * 数据范围：0 < n \le 1000000<n≤100000
 * 要求：时间复杂度 O(nlogn)
 */
public class Solution {
    public ListNode sortInList(ListNode head) {
        if(head==null || head.next==null){//定义递归结束条件（链表的最小长度为1）
            return head;
        }
        //利用快慢指针寻找链表的中点
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode tmp = slow.next; //记录中点下一个节点
        slow.next = null;//拆分两个链表
        //开始归并排序
        ListNode left = sortInList(head);
        ListNode right = sortInList(tmp);
        //创建哑结点用于保存结果链表的起始位置
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while(left!=null && right!=null){
            if(left.val<right.val){
                cur.next = left;
                left = left.next;
            }
            else{
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        //将未排完的链表添加到结果的最后端
        cur.next = left!=null?left:right;
        return dummy.next;
    }

}