package com.one.algorithm.array;

/**
 * 二维数组查找
 * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * @author: one
 */
public class ArrayFind {

    /**
     * 方法：
     * 矩阵是有序的，从左下角来看，向上数字递减，向右数字递增， 
     * 因此从左下角开始查找，当要查找数字比左下角数字大时。右移 要查找数字比左下角数字小时，上移。这样找的速度最快。
     * @return
     */
    public boolean find(int target, int[][] array){
        //基本思路从左下角开始找，这样速度最快
        int row = array.length -1;  //行
        int col = 0;
        while ((row>=0) && (col<array[0].length)){
            if (array[row][col] > target){
                row -- ;
            }else if (array[row][col] < target){
                col ++ ;
            }else {
                return true;
            }
        }
        return false;
        
    }
}
