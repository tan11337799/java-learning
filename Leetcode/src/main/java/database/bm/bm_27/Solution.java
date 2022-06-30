package database.bm.bm_27;

import basic.binarytree.TreeNode;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;

/**
 * @Project: Leetcode
 * @Package: database.bm.bm_27
 * @Date: 2022/6/28 10:27
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 * BM27 按之字形顺序打印二叉树
 * 描述
 * 给定一个二叉树，返回该二叉树的之字形层序遍历，（第一层从左向右，下一层从右向左，一直这样交替）
 *
 * 数据范围：0 \le n \le 15000≤n≤1500,树上每个节点的val满足 |val| <= 1500∣val∣<=1500
 * 要求：空间复杂度：O(n)O(n)，时间复杂度：O(n)O(n)
 * 例如：
 * 给定的二叉树是{1,2,3,#,#,4,5}
 * 该二叉树之字形层序遍历的结果是
 * [
 * [1],
 * [3,2],
 * [4,5]
 * ]
 */
public class Solution {
	//使用栈+层次遍历实现之字形遍历 JZ77
	public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
		if(pRoot==null){
			return res;
		}
		Deque<TreeNode> q = new ArrayDeque<>();
		q.offer(pRoot);
		int index = 1;
		while(!q.isEmpty()){
			ArrayList<Integer> temp = new ArrayList<>();
			int size = q.size();

			for(int i=0;i<size;i++){
				TreeNode node = q.poll();
				temp.add(node.val);
				if(node.left!=null){
					q.offer(node.left);
				}
				if(node.right!=null){
					q.offer(node.right);
				}
			}
			if(index%2==0){
				res.add(temp);
			}else{
				Collections.reverse(temp);
				res.add(temp);
			}
			index+=1;
		}
		return res;
	}

	@Test
	public void test(){

	}
}
