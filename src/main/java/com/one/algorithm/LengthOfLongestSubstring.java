package com.one.algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个字符串，找出不含有重复字符的最长子串的长度。
 * 输入: "abcabcbb"("pwwkew")
 * 输出: 3(3)
 * @author: one
 */
public class LengthOfLongestSubstring {

    /**
     * 优化一
     * 在暴力法中，我们会反复检查一个子字符串是否含有有重复的字符，但这是没有必要的。
     * 如果从索引 i到 j−1之间的子字符串 sij已经被检查为没有重复字符。我们只需要检查 s[j] 对应的字符是否已经存在于子字符串 sij​ 中。
     * 我们使用 HashSet 将字符存储在当前窗口 [i,j)（最初 j=i）中。 然后我们向右侧滑动索引j，如果它不在 HashSet 中，我们会继续滑动 j。
     * 直到 s[j] 已经存在于 HashSet 中。此时，我们找到的没有重复字符的最长子字符串将会以索引 i 开头。
     * 时间复杂度：O(2n)=O(n) 
     * 
     * @param s = "abcabcbb"
     * @return
     */
    public int substring2(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    /**
     * 优化二
     * 上述的方法最多需要执行 2n 个步骤。事实上，它可以被进一步优化为仅需要 n 个步骤。
     * 我们可以定义字符到索引的映射，而不是使用集合来判断一个字符是否存在。 当我们找到重复的字符时，我们可以立即跳过该窗口。
     * 也就是说，如果 s[j] 在 [i,j) 范围内有与 j′重复的字符，我们不需要逐渐增加 i。 
     * 我们可以直接跳过 [i，j′] 范围内的所有元素，并将i变为j′+1。
     * 时间复杂度：O(n) 
     * 
     * @param s = "abcabcbb"
     * @return
     */
    public int substring3(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }
    

    /**
     * 暴力法
     * 时间复杂度：O(n^3) 
     * @param s = "abcabcbb"
     * @return
     */
    public int substring1(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j <= n; j++)
                if (allUnique(s, i, j)) ans = Math.max(ans, j - i);
        return ans;
    }

    public boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            Character ch = s.charAt(i);
            if (set.contains(ch)) return false;
            set.add(ch);
        }
        return true;
    }
}
