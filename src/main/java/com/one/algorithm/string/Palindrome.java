package com.one.algorithm.string;

import java.util.HashSet;
import java.util.Set;

/**
 * 回文串
 * 
 * @author: xiaokang.zhu
 */
public class Palindrome {

    /**
     * 最长回文串
     * LeetCode: 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。在构造过程中，请注意区分大小写。
     * 比如"Aa"不能当做一个回文字符串。注 意:假设字符串的长度不会超过 1010。
     *
     * 回文串：“回文串”是一个正读和反读都一样的字符串，比如“level”或者“noon”等等就是回文串。
     * 1.字符出现次数为双数的组合
     * 2.字符出现次数为双数的组合+一个只出现一次的字符
     */
    public int longestPalindrome(String s){
        if (s.length() == 0){
            return 0;
        }
        int count = 0;
        Set<Character> set = new HashSet<Character>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!set.contains(c)){
                set.add(c);
            }else {
                set.remove(c);
                count++;
            }
        }
        return set.isEmpty()?count*2 : count*2+1;
    }

    /**
     * 验证回文串
     * LeetCode: 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。 
     * 说明：本题中，我们将空字符串定义为有效的回文串。
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        if (s.length() == 0){
            return true;
        }
        int l = 0, r = s.length()-1;
        while (l <r){
            if (!Character.isLetterOrDigit(s.charAt(l))){
                l++;
            }else if (!Character.isLetterOrDigit(s.charAt(r))){
                r--;
            }else {
                if (Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r))){
                    return false;
                }
                l++;
                r--;
            }
        }
        return true;
    }

    /**
     * 最长回文子串
     * LeetCode: 最长回文子串 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为1000。
     * 以某个元素为中心，分别计算偶数长度的回文最大长度和奇数长度的回文最大长度。
     */
    private int index, len;
    public String longestPalindromeSub(String s) {
        int n = s.length();
        if (n<=1) return s;

        for (int i=0; i<n-1; i++) {
            PalindromeHelper(s, i, i);
            PalindromeHelper(s, i, i + 1);
        }
        return s.substring(index, index + len);
    }
    public void PalindromeHelper(String s, int left, int right) {
        int l = left;
        int r = right;
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        if (len < r - l - 1) {
            index = l + 1;
            len = r - l - 1;
        }
    }

    /**
     * 最长回文子序列
     */
    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        for (int i = len-1; i >=0; i--) {
            dp[i][i] = 1;
            for (int j=i+1; j<len; j++){
                if (s.charAt(i) == s.charAt(j)){
                    dp[i][j] = dp[i+1][j-1] + 2;
                }else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][len-1];
    }
    

    public static void main(String[] args) {
        Palindrome l = new Palindrome();
        
        System.out.println(l.longestPalindrome("abccccdd"));
        System.out.println(l.isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(l.longestPalindromeSub("babad"));
        System.out.println(l.longestPalindromeSubseq("bbbab"));
    }
}
