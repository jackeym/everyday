package com.one.algorithm.string;

import java.util.Arrays;

/**
 * 最长公共前缀
 * Leetcode: 编写一个函数来查找字符串数组中的最长公共前缀。如果不存在公共前缀，返回空字符串 ""。
 * @author: one
 */
public class CommonPrefix {
    
    public String commonPrefix(String[] strs){
        int len = strs.length;
        if (strs == null || strs.length == 0) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        Arrays.sort(strs);
        int m = strs[0].length();
        int n = strs[len-1].length();
        int num = Math.min(m,n);
        for (int i = 0; i < num; i++) {
            if (strs[0].charAt(i) == strs[len-1].charAt(i)){
                sb.append(strs[0].charAt(i));
            }else {
                break;
            }
        }
        return sb.toString();
    }

    //测试
    public static void main(String[] args) {
        String[] strs = { "customer", "car", "cat" };
        CommonPrefix test = new CommonPrefix();
        System.out.println(test.commonPrefix(strs));//c
    }
}
