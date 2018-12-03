package com.one.algorithm.linkedlist;

/**
 * @author: one
 */
public class isLoop {
    
    public boolean isLoop(ListNode node){
        ListNode slow = node;
        ListNode fast = node.next;
        while (slow != null){
            int dateSlow = slow.val;
            int dataFast = fast.val;
            if (dataFast == dateSlow){
                return false;
            }
            
            if (fast.next == null){
                return false;
            }
            
            slow = slow.next;
            fast = fast.next.next;
            if (fast == null){
                return false;
            }
        }
        return true;
    }
}
