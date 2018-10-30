package com.one.algorithm.stack;

import java.util.Stack;

/**
 * 栈的压入、弹出序列
 * 
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。
 * 例如序列1,2,3,4,5是某栈的压入顺序，序列4，5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。
 * （注意：这两个序列的长度是相等的）
 * @author: one
 */
public class IsPopOrder {

    /**
     * 借用一个辅助的栈，遍历压栈顺序，先讲第一个放入栈中，这里是1，然后判断栈顶元素是不是出栈顺序的第一个元素，这里是4，很显然1≠4，
     * 所以我们继续压栈，直到相等以后开始出栈，出栈一个元素，则将出栈顺序向后移动一位，直到不相等，这样循环等压栈顺序遍历完成，
     * 如果辅助栈还不为空，说明弹出序列不是该栈的弹出顺序。
     * 
     * https://github.com/Snailclimb/JavaGuide/blob/master/%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84%E4%B8%8E%E7%AE%97%E6%B3%95/%E7%AE%97%E6%B3%95%E9%A2%98%E8%A7%A3%E6%9E%90/%E5%89%91%E6%8C%87offer/%EF%BC%885%EF%BC%89%E6%A0%88%E5%8F%98%E9%98%9F%E5%88%97%E5%92%8C%E6%A0%88%E7%9A%84%E5%8E%8B%E5%85%A5%E3%80%81%E5%BC%B9%E5%87%BA%E5%BA%8F%E5%88%97.md
     */
    
    public boolean isPopOrder(int[] pushA, int[] popA){
        if (pushA.length == 0 || popA.length == 0){
            return false;
        }
        Stack<Integer> s = new Stack<Integer>();
    
        //用于标识弹出序列的位置
        int popIndex = 0;
        
        for (int i = 0; i < pushA.length; i++) {
            s.push(pushA[i]);
            //如果栈不为空，且栈顶元素等于弹出序列
            while (!s.empty() && s.peek() == popA[popIndex]){
                s.pop();
                popIndex ++;
            }
        }
        return s.empty();
    }
}
