package basic.linkedlist;

/**
 * @Project: Leetcode
 * @Package: basic.LinkedList
 * @Date: 2022/6/27 19:40
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public class LLToolbox {
    public void printLinkedList(ListNode head){
        ListNode cur = head;
        while((cur.next)!=null){
            System.out.print(cur.val+"->");
            cur = cur.next;
        }
        System.out.println(cur.val);
    }

    public ListNode createLinkedList(int[] array){
        ListNode head = new ListNode();
        ListNode cur = head;
        for(int i=0;i<array.length;i++){
            cur.next = new ListNode(array[i]);
            cur = cur.next;
        }
        return head.next;
    }

    public ListNode reverseLinkedList(ListNode head){
        ListNode pre = null;
        ListNode cur = head;
        while(cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

}
