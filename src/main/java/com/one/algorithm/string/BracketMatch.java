package com.one.algorithm.string;

/**
 * 括号匹配
 * @author: one
 * @version: 1.0
 */
public class BracketMatch {

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
    
    public int match(String s){
        int cnt = 0, max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '('){
                cnt++;
            }else {
                cnt--;
            }
            max = Math.max(max, cnt);
        }
        return max;
    }

    public static void main(String[] args) {
        BracketMatch test = new BracketMatch();
        System.out.println(test.match("((()))()"));
    }
}
