package database.bm.bm_33;

import basic.binarytree.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Project: Leetcode
 * @Package: database.bm.bm_33
 * @Date: 2022/6/28 10:37
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 * BM33 二叉树的镜像
 * 操作给定的二叉树，将其变换为源二叉树的镜像。
 * 数据范围：二叉树的节点数 0 \le n \le 10000≤n≤1000 ， 二叉树每个节点的值 0\le val \le 10000≤val≤1000
 * 要求： 空间复杂度 O(n)O(n) 。本题也有原地操作，即空间复杂度 O(1)O(1) 的解法，时间复杂度 O(n)O(n)
 */
public class Solution {
    public TreeNode Mirror (TreeNode pRoot) {
        // write code here
        if(pRoot==null){
            return pRoot;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(pRoot);
        while(!stack.isEmpty()){
            TreeNode temp = stack.pop();

            if(temp.left!=null) stack.push(temp.left);
            if(temp.right!=null) stack.push(temp.right);

            TreeNode cur = temp.left;
            temp.left = temp.right;
            temp.right = cur;
        }
        return pRoot;
    }
}
