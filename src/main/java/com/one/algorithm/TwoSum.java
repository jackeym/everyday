package com.one.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 * @author one
 */
public class TwoSum {

    /**
     * 时间复杂度为 O(N^2)
     * @param nums
     * @param target
     * @return
     */
    public int[] getTwo1(int[] nums,int target){
        int[] result = new int[2] ;

        for (int i= 0 ;i<nums.length ;i++){
            int a = nums[i] ;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    result = new int[] { i, j };
                    break;
                }
            }
        }
        return result ;
    }


    /**
     * 时间复杂度 O(N)
     * 利用Map Key存放目标值和当前值的差值，value 就是当前的下标
     * 每次遍历是 查看当前遍历的值是否等于差值，如果是等于，说明两次相加就等于目标值。
     * 然后取出 map 中 value ，和本次遍历的下标，就是两个下标值相加等于目标值了。
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] getTwo2(int[] nums,int target){
        int[] result = new int[2] ;
        Map<Integer,Integer> map = new HashMap<>(2) ;
        for (int i=0 ;i<nums.length;i++){
            int complement = target - nums[i];
            if (map.containsKey(complement)){
                result = new int[]{map.get(complement),i} ;
                break;
            }
            map.put(nums[i], i) ;
        }
        return result ;
    }
}
