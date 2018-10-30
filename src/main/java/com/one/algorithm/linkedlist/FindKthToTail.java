package com.one.algorithm.linkedlist;

/**
 * 链表中倒数第k个节点
 * 输入一个链表，输出该链表中倒数第k个结点(只循环一次,时间复杂度O(n))
 * 
 * @author: one
 */
public class FindKthToTail {
    /**
     * 为了实现仅仅遍历链表一次就能找到倒数第k个结点,我们需要定义两个指针。
     * 第一个指针从链表的头指针开始遍历向前走k-1。第二个指针保持不动；从第k步开始，第二个指针也开始从链表的头指针开始遍历。
     * 因为两个指针的距离保持在k-1。当第一个（走在前面的）指针到达链表的尾指结点时，第二个指针正好是倒数第k个结点。
     */
    
    public ListNode findKthToTail(ListNode head, int k){
        if (head == null || k < 0) { 
            return null;
        }
        ListNode p = head, pre = head;
        int a = k;
        int count = 0;
        
        //p指针先跑，并且记录节点数，当p指针跑了k-1个节点后，pre指针开始跑，
        //当p指针跑到最后时，pre所指指针就是倒数第k个节点
        while (p != null){
            p = p.next;
            if (k < 1){
                pre = pre.next;
            }
            count ++;
            k --;
        }
        //如果节点个数小于所求的倒数第k个节点，则返回空
        if (count < a) return null; 
        return pre;
    }
    
    
    public class ListNode{
        int val;
        ListNode next = null;
        
        ListNode(int val){
            this.val = val;
        }
    }

    public static void main(String[] args) {
        FindKthToTail fk = new FindKthToTail();
        ListNode ln1 = fk.new ListNode(1);
        ListNode ln2 = fk.new ListNode(2);
        ListNode ln3 = fk.new ListNode(3);
        ListNode ln4 = fk.new ListNode(4);
        ListNode ln5 = fk.new ListNode(5);
        ListNode ln6 = fk.new ListNode(6);
        ListNode ln7 = fk.new ListNode(7);
        ListNode ln8 = fk.new ListNode(8);
        ln1.next = ln2;
        ln2.next = ln3;
        ln3.next = ln4;
        ln4.next = ln5;
        ln5.next = ln6;
        ln6.next = ln7;
        ln7.next = ln8;
        ln8.next = null;
        System.out.println(fk.findKthToTail(ln1, 3).val);
    }
}
