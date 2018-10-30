package com.one.algorithm.linkedlist;

/**
 * 反转链表
 * 输入一个链表，反转链表后，输出链表的所有元素。
 * @author: one
 */
public class ReverseList {

    /**
     * 思路就是我们根据链表的特点，前一个节点指向下一个节点的特点，把后面的节点移到前面来。
     * 就比如下图：我们把1节点和2节点互换位置，然后再将3节点指向2节点，4节点指向3节点，这样以来下面的链表就被反转了。 
     * https://github.com/Snailclimb/JavaGuide/blob/master/%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84%E4%B8%8E%E7%AE%97%E6%B3%95/%E7%AE%97%E6%B3%95%E9%A2%98%E8%A7%A3%E6%9E%90/%E5%89%91%E6%8C%87offer/%EF%BC%884%EF%BC%89%E9%93%BE%E8%A1%A8%E7%9B%B8%E5%85%B3%E7%BC%96%E7%A8%8B%E9%A2%98.md
     */
    
    public ListNode reverseList(ListNode head){
        ListNode next = null, pre = null;
        while (head != null){
            next = head.next;  //保存要反转到头来的那个节点
            head.next = pre;    //要反转的那个节点指向已经反转的上一个节点
            pre = head; //上一个已经反转到头部的节点
            head = next;    //一直向链表尾走
        }
        return pre;
    }
    
    public class ListNode{
        int val;
        ListNode next = null;

        ListNode(int val){
            this.val = val;
        }
    }
}
