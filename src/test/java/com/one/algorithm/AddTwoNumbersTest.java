package com.one.algorithm;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

/**
 * @author: one
 */
public class AddTwoNumbersTest {

    @Test
    public void addTwo1() throws Exception {
        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
        AddTwoNumbers.ListNode l11 = addTwoNumbers.new ListNode(2);
        AddTwoNumbers.ListNode l12 = addTwoNumbers.new ListNode(4);
        AddTwoNumbers.ListNode l13 = addTwoNumbers.new ListNode(3);
        l11.next = l12;
        l12.next = l13;
        
        AddTwoNumbers.ListNode l21 = addTwoNumbers.new ListNode(5);
        AddTwoNumbers.ListNode l22 = addTwoNumbers.new ListNode(6);
        AddTwoNumbers.ListNode l23 = addTwoNumbers.new ListNode(4);
        l21.next = l22;
        l22.next = l23;

        AddTwoNumbers.ListNode re = addTwoNumbers.add(l11, l21);
        System.out.println(re.toString());
        
    }
}
