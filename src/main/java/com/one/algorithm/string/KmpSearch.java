package com.one.algorithm.string;

/**
 * KMP 算法
 * @author: one
 */
public class KmpSearch {
    
    public int kmp(String s, String w){
        int[] next = getNext(w);
        int i = 0, j = 0;
        while (i < s.length() && j < w.length()) {
            if (j == -1 || s.charAt(i) == w.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j >= w.length()) {
            return (i - w.length());
        } else {
            return 0;
        }
    } 
    
    public int[] getNext(String w){
        int len = w.length();
        int[] next = new int[len];
        next[0] = -1;
        int i = 0, j = -1;
        while (i < len-1) {
            if (j == -1 || w.charAt(i) == w.charAt(j)) {
                next[++i] = ++j;
            } else {
                j = next[j];
            }
        }
        return next;
    }

    public static void main(String[] args) {
        KmpSearch ks = new KmpSearch();
        int pos = ks.kmp("BBC ABCDAB ABCDABCDABDE","ABCDABD");
        System.out.println(pos);
    }
}
