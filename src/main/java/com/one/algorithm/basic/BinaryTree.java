package com.one.algorithm.basic;

import java.util.Stack;

/**
 * 二叉树
 *
 * @author: one
 */
public class BinaryTree {

    public class TreeNode {
        public TreeNode parent;
        public String data;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(String data) {
            this.data = data;
        }

        public TreeNode(TreeNode parent, String data) {
            this.parent = parent;
            this.data = data;
        }

        public TreeNode(String data, TreeNode left, TreeNode right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public TreeNode(TreeNode parent, String data, TreeNode left, TreeNode right) {
            this.parent = parent;
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }


    /**
     * 递归遍历
     */
    public static void travelPreOrder(TreeNode root) {
        // 前序遍历， 遍历顺序： parent -> left -> child 
        if (root == null) {
            return;
        }
        System.out.println(root.data);
        travelPreOrder(root.left);
        travelPreOrder(root.right);
    }

    public static void travelMidOrder(TreeNode root) {
        // 中序遍历， 遍历顺序： left -> parent -> child 
        if (root == null) {
            return;
        }
        travelMidOrder(root.left);
        System.out.println(root.data);
        travelMidOrder(root.right);
    }

    public static void travelPostOrder(TreeNode root) {
        // 后序遍历， 遍历顺序： left -> child -> parent 
        if (root == null) {
            return;
        }
        travelPostOrder(root.left);
        travelPostOrder(root.right);
        System.out.println(root.data);
    }

    /**
     * 非递归实现
     */
    public static void travelPre(TreeNode root) {
        // 前序遍历
        if (root == null){
            return;
        }
        
        TreeNode node = root;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                System.out.println(node.data);
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                node = stack.pop();
                node = node.right;
            }
        }
    }
    
    public static void travelMid(TreeNode root){
        // 中序遍历
        if (root == null){
            return;
        }
        
        TreeNode node = root;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || node != null){
            while (node != null){
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()){
                node = stack.pop();
                System.out.println(node.data);
                node = node.right;
            }
        }
    }

    public static void travelPost(TreeNode node){
        // 后序遍历
        if (node == null){
            return;
        }
        
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur;   //当前节点
        TreeNode pre = null;    //前一次访问的节点
        stack.push(node);
        while (!stack.isEmpty()){
            cur = stack.peek();
            if ((cur.left == null && cur.right == null) || (pre != null && (pre == cur.left||pre == cur.right))){
                //如果当前节点没有孩子节点，或者孩子节点已经被访问过
                System.out.println(cur.data);
                stack.pop();
                pre = cur;
            }else {
                if (cur.right != null){
                    stack.push(cur.right);
                }
                if (cur.left != null){
                    stack.push(cur.left);
                }
            }
        }
    }

}
