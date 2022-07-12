package basic.binarytree;

import java.util.*;

/**
 * @Project: LeetCode
 * @Package: basic.backtrack.BinaryTree.basic
 * @Date: 2022/6/17 15:15
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */


// 深度优先遍历
public class depthFirstTraverse {
    //////////////////////////////////////////////////////////////
    // 递归法
    //////////////////////////////////////////////////////////////
    // 前序遍历
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        preorder(root, res);
        return res;
    }

    void preorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        preorder(root.left, res);
        preorder(root.right, res);
    }
    // 中序遍历
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res;
    }

    void inorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inorder(root.left, list);
        list.add(root.val);             // 注意这一句
        inorder(root.right, list);
    }
    // 后序遍历
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postorder(root, res);
        return res;
    }

    void postorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        postorder(root.left, list);
        postorder(root.right, list);
        list.add(root.val);             // 注意这一句
    }

    //////////////////////////////////////////////////////////////
    // 迭代法
    //////////////////////////////////////////////////////////////
    //前序遍历
    public List<Integer> preorderTraversal_iter(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        if(root==null){
            return res;
        }
        while(!stack.isEmpty()){
            TreeNode temp = stack.pop();
            res.add(temp.val);
            //先右节点再左节点，出栈时才是先左后右
            if(temp.right!=null){
                stack.push(temp.right);
            }
            if(temp.left!=null){
                stack.push(temp.left);
            }
        }
        return res;
    }

    //中序遍历（使用栈记录树的元素，使用指针遍历节点）
    public List<Integer> inorderTraversal_iter(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root==null){
            return res;
        }

        //栈中存放元素实际上是每个子二叉树的中间节点
        Deque<TreeNode> stack = new LinkedList<>();
        //cur表示当前处理的节点
        TreeNode cur = root;
        while(cur!=null || !stack.isEmpty()){
            //如果指针指向的节点不为空，则将当前元素推入栈中，指针指向当前节点的左节点（不管左节点是否为null,都进行这一操作）
            if(cur!=null){
                stack.push(cur);
                cur = cur.left;
            }
            //如果发现当前处理节点为空，则从栈中取出元素进行添加，将节点指向右节点
            //第一次为空意味着该节点的左节点已处理完毕，从栈中移出该节点，开始处理右节点；
            //第二次为空则意味着该节点的右节点也没有内容，则该节点处理完毕，处理栈的下一个顶部元素（不一定为cur的父节点）
            else{
                cur = stack.pop();
                res.add(cur.val);
                cur = cur.right;
            }
        }
        return res;
    }
}