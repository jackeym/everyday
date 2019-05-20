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



    // a, b 分别是主串和模式串；n, m 分别是主串和模式串的长度。
    public static int kmp2(char[] a, char[] b) {
        int n = a.length;
        int m = b.length;
        
        int[] next = getNexts(b, m);
        int j = 0;
        for (int i = 0; i < n; ++i) {
            while (j > 0 && a[i] != b[j]) { // 一直找到 a[i] 和 b[j]
                j = next[j - 1];
            }
            if (a[i] == b[j]) {
                j++;
            }
            if (j == m) { // 找到匹配模式串的了
                return i - m + 1;
            }
        }
        return -1;
    }

    // b 表示模式串，m 表示模式串的长度
    private static int[] getNexts(char[] b, int m) {
        int[] next = new int[m];
        next[0] = 0;
        int k = 0;
        for (int i = 1; i < m; ++i) {
            while (k > 0 && b[k] != b[i]) {
                k = next[k-1];
            }
            if (b[k] == b[i]) {
                k++;
            }
            next[i] = k;
        }
        return next;
    }




    public static void main(String[] args) {
        KmpSearch ks = new KmpSearch();
        int pos = ks.kmp("BBC ABCDAB ABCDABCDABDE","ABCDABD");
        System.out.println(pos);
    }
}
