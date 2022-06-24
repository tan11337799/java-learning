package basic.BinaryTree;

import com.sun.source.tree.Tree;

import java.lang.reflect.Array;
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

    //使用队列实现之字形遍历 JZ77
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if(pRoot==null){
            return res;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(pRoot);
        int index = 1;
        while(!stack.isEmpty()){
            ArrayList<Integer> temp = new ArrayList<>();
            int size = stack.size();

            for(int i=0;i<size;i++){
                TreeNode node = stack.pop();
                temp.add(node.val);
                if(node.left!=null){
                    stack.push(node.left);
                }
                if(node.right!=null){
                    stack.push(node.right);
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
}