package com.one.algorithm;

import com.alibaba.fastjson.JSON;
import com.one.algorithm.linkedlist.AddTwoNumbers;
import com.one.algorithm.linkedlist.ListNode;
import org.junit.Test;

/**
 * @author: one
 */
public class AddTwoNumbersTest {

    @Test
    public void addTwo1() throws Exception {
        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
        ListNode l11 = new ListNode(2);
        ListNode l12 = new ListNode(4);
        ListNode l13 = new ListNode(3);
        l11.next = l12;
        l12.next = l13;
        
        ListNode l21 = new ListNode(5);
        ListNode l22 = new ListNode(6);
        ListNode l23 = new ListNode(4);
        l21.next = l22;
        l22.next = l23;

        ListNode re = addTwoNumbers.addTwo(l11, l21);
        System.out.println(re.toString());
        
    }
}
