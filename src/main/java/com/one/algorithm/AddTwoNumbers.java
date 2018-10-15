package com.one.algorithm;

/**
 * @author: one
 */
public class AddTwoNumbers {

    public ListNode add(ListNode l1, ListNode l2) {
        ListNode resultHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = resultHead;
        int carry = 0;
        while (p != null || q != null){
            int x = (p != null)?p.val:0;
            int y = (q != null)?q.val:0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        
        return resultHead.next;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }

        @Override
        public String toString() {
            String str = "";
            ListNode head = this;
            while (head != null){
                str = head.val + str;
                head = head.next;
            }
            return str;
        }
    }

}
