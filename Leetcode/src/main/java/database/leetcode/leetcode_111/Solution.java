package database.leetcode.leetcode_111;

import basic.binarytree.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Project: Leetcode
 * Package: database.leetcode.leetcode_111
 * Date: 2022/7/13 10:24
 * Author: Wenhao Tan
 * Version: 1.0
 * License: (C)2022,MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
class Solution {
    //dfs
    public int minDepth(TreeNode root) {
        //除非是根节点，否则不会经过此判断
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int min_depth = Integer.MAX_VALUE;
        if (root.left != null) {
            min_depth = Math.min(minDepth(root.left), min_depth);
        }
        if (root.right != null) {
            min_depth = Math.min(minDepth(root.right), min_depth);
        }
        return min_depth + 1;
    }

    //bfs
    public int minDepth_bfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        int depth = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            depth++;
            for (int i = 0; i < size; i++) {
                TreeNode temp = q.poll();
                if(temp.left==null && temp.right==null){
                    return depth;
                }
                if (temp.left != null) {
                    q.offer(temp.left);
                }
                if (temp.right != null) {
                    q.offer(temp.right);
                }
            }
        }
        return depth;
    }
}