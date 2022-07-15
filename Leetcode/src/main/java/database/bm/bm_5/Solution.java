package database.bm.bm_5;

import basic.linkedlist.ListNode;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Project: Leetcode
 * Package: database.bm.bm_5
 * Date: 2022/7/13 23:33
 * Author: Wenhao Tan
 * Version: 1.0
 * License: (C)2022,MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public class Solution {
	public ListNode mergeKLists(ArrayList<ListNode> lists) {
		Queue<ListNode> pq = new PriorityQueue<>((v1, v2)->v1.val-v2.val);
		for(ListNode l:lists){
			if(l!=null){
				pq.offer(l);
			}
		}
		ListNode res = new ListNode(0);
		ListNode cur = res;
		while(!pq.isEmpty()){
			ListNode temp = pq.poll();
			cur.next = temp;
			cur = cur.next;
			if(temp.next!=null){
				pq.offer(temp.next);
			}
		}
		return res;
	}
}
