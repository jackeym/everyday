package com.one.algorithm.string;

import com.one.algorithm.stack.LinkedStack;
import com.one.algorithm.stack.MyStack;

import java.util.Stack;


/**
 * 括号匹配校验
 * @author: one
 * @version: 1.0
 */
public class BracketValid {

    /**
     * 括号匹配深度
     * 
     * 输入描述:
     * 输入包括一个合法的括号序列s,s长度length(2 ≤ length ≤ 50),序列中只包含'('和')'。
     * 例如: "","()","()()","((()))"都是合法的括号序列
     *
     * 输出描述:
     * 输出一个正整数,即这个序列的深度。
     */

    public static String isValid(String expstr)
    {
        //创建栈
        MyStack<String> stack = new LinkedStack<>();

        int i=0;
        while(i<expstr.length())
        {
            char ch=expstr.charAt(i);
            i++;
            switch(ch)
            {
                case '(': stack.push(ch+"");//左括号直接入栈
                    break;
                case ')': if (stack.isEmpty() || !stack.pop().equals("(")) //遇见右括号左括号直接出栈
                    return "check exception!";
            }
        }
        //最后检测是否为空,为空则检测通过
        if(stack.isEmpty())
            return "check pass!";
        else
            return "check exception!";
    }

    public static void main(String args[])
    {
        String expstr="((5-3)*8-2)";
        System.out.println(expstr+"  "+isValid(expstr));
    }
}
