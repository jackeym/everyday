package com.one.algorithm.linkedlist;

/**
 * 合并两个排序的链表
 * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
 * @author: one
 */
public class MergeSortedList {

    /**
     * 我们可以这样分析:
     *     假设我们有两个链表 A,B；
     *     A的头节点A1的值与B的头结点B1的值比较，假设A1小，则A1为头节点；
     *     A2再和B1比较，假设B1小,则，A1指向B1；
     *     A2再和B2比较。。。。。。。 就这样循环往复就行了，应该还算好理解。
     */
    public ListNode merge(ListNode list1, ListNode list2){
        if (list1 == null){
            return list2;
        }
        if (list2 == null){
            return list1;
        }
        ListNode mHead = null, current = null;
        while (list1 != null && list2 != null){
            if (list1.val < list2.val){
                if (current == null){
                    current = mHead = list1;
                }else {
                    current.next = list1;
                    current = list1;
                }
                list1 = list1.next;
            }else {
                if (current == null){
                    current = mHead = list2;
                }else {
                    current.next = list2;
                    current = list2;
                }
                list2 = list2.next;
            }
        }
        if (list1 == null){
            current.next = list2;
        }
        if (list2 == null){
            current.next = list1;
        }
        return mHead;
    }

    public class ListNode{
        int val;
        ListNode next = null;

        ListNode(int val){
            this.val = val;
        }
    }
}
