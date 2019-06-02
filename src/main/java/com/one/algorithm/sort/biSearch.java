package com.one.algorithm.sort;

/**
 * 二分查找
 * @Author: one
 */
public class biSearch {

    public static int biSearch(int[] arr, int a){
        int lo = 0;
        int hi = arr.length - 1;
        int mid;
        while (lo<=hi){
            mid = (lo + hi)/2;
            if (arr[mid] == a){
                return mid + 1;
            }else if (arr[mid] < a){    //向右查找
                lo = mid + 1;
            }else {     //向左查找
                hi = mid + 1;
            }
        }
        return -1;
    }
}
