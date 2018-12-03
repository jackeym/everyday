package com.one.algorithm.linkedlist;

/**
 * @author: one
 */
public class LinkedListSort {
    
    public ListNode mergeSort(ListNode first, int length){
        if (length == 1){
            return first;
        }
        
        ListNode middle = new ListNode();
        ListNode tmp = first;
        // 找出中间元素
        for (int i = 0; i < length; i++) {
            if (i == length/2){
                break;
            }
            middle = tmp;
            tmp = tmp.next;
        }

        /**
         *  这里是链表归并时要注意的细节
         *  在链表进行归并排序过程中，会涉及到将一个链表打散为两个独立的链表，所以需要在中间元素的位置将其后续指针指为null；
         */
        ListNode right = middle.next;
        middle.next = null;
        
        ListNode leftStart = mergeSort(first, length/2);
        ListNode rightStart;
        if (length % 2 == 0){
            rightStart = mergeSort(right, length/2);
        }else {
            rightStart = mergeSort(right, length/2 + 1);
        }
        return mergeList(leftStart, rightStart);
    } 
    
    public ListNode mergeList(ListNode left, ListNode right){
        ListNode head = new ListNode();
        ListNode result = head;
        
        while (left != null && right != null){
            if (left.val > right.val){
                result.next = left;
                result = left;
                left = left.next;
            }else {
                result.next = right;
                result = right;
                right = right.next;
            }
        }
        
        if (left != null){
            result.next = left;
        }
        if (right != null){
            result.next = right;
        }
        return head.next;
    }


    public static void main(String[] args) {

        ListNode head = new ListNode();

        head.next = new ListNode(7,
                new ListNode(2,
                        new ListNode(5,
                                new ListNode(4,
                                        new ListNode(3,
                                                new ListNode(6,
                                                        new ListNode(11, null)
                                                )
                                        )
                                )
                        )
                )
        );

        int length = 0;

        for (ListNode e = head.next; null != e; e = e.next) {
            length++;
        }


        LinkedListSort sort = new LinkedListSort();
        head.next = sort.mergeSort(head.next, length);


        for (ListNode n = head.next; n != null; n = n.next) {
            System.out.println(n.val);
        }

    }
}
