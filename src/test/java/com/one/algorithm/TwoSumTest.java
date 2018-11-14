package com.one.algorithm;

import com.alibaba.fastjson.JSON;
import com.one.algorithm.array.TwoSum;
import com.one.algorithm.string.LengthOfLongestSubstring;
import org.junit.Test;

public class TwoSumTest {
    @Test
    public void getTwo1() throws Exception {
        TwoSum twoSum = new TwoSum() ;
        int[] nums ={1,3,5,7};
        int[] two1 = twoSum.getTwo1(nums, 12);
        System.out.println(JSON.toJSONString(two1));

    }

    @Test
    public void getTwo2(){
        TwoSum twoSum = new TwoSum() ;
        int[] nums ={1,3,5,7};
        int[] two = twoSum.getTwo2(nums, 10);
        System.out.println(JSON.toJSONString(two));

    }
    
    @Test
    public void substring1(){
        LengthOfLongestSubstring lengthOfLongestSubstring = new LengthOfLongestSubstring();
        String s = "abcabcbb";
        //s = "pwwkew";
        int re = lengthOfLongestSubstring.substring3(s);
        System.out.println(re);
    }

}