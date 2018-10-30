package com.one.algorithm.array;

/**
 * 调整数组顺序使奇数位于偶数前面
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，
 * 所有的偶数位于位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 * @author: one
 */
public class ReOrderArray {

    /**
     * 介绍一种我觉得挺好理解的方法： 
     * 我们首先统计奇数的个数假设为n,然后新建一个等长数组，然后通过循环判断原数组中的元素为偶数还是奇数。
     * 如果是则从数组下标0的元素开始，把该奇数添加到新数组；
     * 如果是偶数则从数组下标为n的元素开始把该偶数添加到新数组中。
     */
    
    public void reOrderArray(int[] array){
        if (array.length==0 || array.length == 1){
            return;
        }
        //oddCount：保存奇数个数
        //oddBegin：奇数从数组头部开始添加
        int oddCount = 0, oddBegin = 0;
        //新建一个数组
        int[] newArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            if ((array[i]&1) == 1){
                oddCount ++ ;
            }
        }
        for (int i = 0; i < array.length; i++) {
            if ((array[i]&1) == 1){
                newArray[oddBegin++] = array[i];
            }else {
                newArray[oddCount++] = array[i];
            }
        }
        for (int i = 0; i < array.length; i++) {
            array[i] = newArray[i];
        }
    }
}
