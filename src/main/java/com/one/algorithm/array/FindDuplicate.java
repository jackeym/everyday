package com.one.algorithm.array;

import java.util.Arrays;

/**
 * 数组中重复数字
 * https://www.nowcoder.com/questionTerminal/623a5ac0ea5b4e5f95552655361ae0a8
 * 
 * @author: one
 */
public class FindDuplicate {

    /**
     * 不需要额外的数组或者hash table来保存，题目里写了数组里数字的范围保证在0 ~ n-1 之间，
     * 所以可以利用现有数组设置标志，当一个数字被访问过后，可以设置对应位上的数 + n，之后再遇到相同的数时，
     * 会发现对应位上的数已经大于等于n了，那么直接返回这个数即可。 
     * 复杂度为 O(N) + O(1)
     */
    public int find( int numbers[], int length) {

        for ( int i= 0 ; i<length; i++) {
            int index = numbers[i];
            if (index >= length) {
                index -= length;
            }

            if (numbers[index] >= length) {
                return index;
            }
            numbers[index] = numbers[index] + length;
        }
        return - 1 ;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 0, 2, 5};
        FindDuplicate duplicate = new FindDuplicate();
        int o = duplicate.find(arr, 6);
        System.out.println(o);
    }
    
}
