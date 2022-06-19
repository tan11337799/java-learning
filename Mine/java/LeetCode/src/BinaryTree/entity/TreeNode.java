package BinaryTree.entity;

/**
 * @Project: LeetCode
 * @Package: BinaryTree.basic.entity
 * @Date: 2022/6/17 15:16
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(){};
    public TreeNode(int val){this.val = val;};

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
