package com.one.algorithm.tree;

import java.util.Stack;

/**
 * 给定一个二叉树，原地将它展开为链表。
 * 例如，给定二叉树
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * 将其展开为：
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 * 这道题目的关键是转换的时候递归的时候记住是先转换右子树，再转换左子树。
 * 所以需要记录一下右子树转换完之后链表的头结点在哪里。注意没有新定义一个next指针，而是直接将right 当做next指针,那么Left指针我们赋值成null就可以了。
 *
 * @date: 2019/4/9
 * @version: 1.0
 */
public class FlattenBTree {
    private TreeNode prev = null;

    public void flatten(TreeNode root) {
        if (root == null)  return;
        flatten(root.right); // 先转换右子树
        flatten(root.left);
        root.right = prev;  // 右子树指向链表的头
        root.left = null; // 把左子树置空
        prev = root; // 当前结点为链表头
    }


    /**
     * 用递归解法,用一个stack 记录节点,右子树先入栈，左子树后入栈。
     * @param root
     */
    public void flatten2(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            if (current.right != null) stack.push(current.right);
            if (current.left != null) stack.push(current.left);
            if (!stack.isEmpty()) current.right = stack.peek();
            current.left = null;
        }
    }

}
