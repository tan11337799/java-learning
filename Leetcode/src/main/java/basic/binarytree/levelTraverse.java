package basic.binarytree;

import java.util.*;

/**
 * @Project: LeetCode
 * @Package: basic.backtrack.BinaryTree.basic
 * @Date: 2022/6/17 16:06
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public class levelTraverse {
    //使用队列实现层次遍历
    public List<Integer> levelTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> q = new ArrayDeque<>();
        if (root == null) {
            return res;
        }
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode temp = q.poll();
            res.add(temp.val);
            if (temp.left != null) {
                q.offer(temp.left);
            }
            if (temp.right != null) {
                q.offer(temp.right);
            }
        }
        return res;
    }


}