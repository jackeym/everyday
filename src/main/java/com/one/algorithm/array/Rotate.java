package com.one.algorithm.array;

/**
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 * 输入: [1,2,3,4,5,6,7] 和 k = 3
   输出: [5,6,7,1,2,3,4]
 * @Author: one
 */
public class Rotate {
    /**
     k = k % nums.length; //旋转后的位置
     int[] result = new int[nums.length];
     for(int i = 0; i<nums.length; i++){
     result[(i+k)%nums.length] = nums[i];
     }
     return result;
     */
    public void rotate(int[] nums, int k) {

        if(nums.length ==0 || (k%=nums.length)==0) return;
        int length = nums.length;
        int start = 0;
        int i = 0;
        int cur = nums[i];
        int j = 0;
        while(j++ < length){
            i = (i+k) % length;
            int tmp = nums[i];
            nums[i] = cur;
            if(i == start){
                ++start;
                ++i;
                cur = nums[i];
            }else{
                cur = tmp;
            }

        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5,6};
        int k = 2;
        Rotate r = new Rotate();
        r.rotate(nums, k);
    }
}
