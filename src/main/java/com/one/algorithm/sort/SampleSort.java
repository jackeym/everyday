package com.one.algorithm.sort;

import java.util.Arrays;

/**
 * @author: one
 */
public class SampleSort {

    /**
     * 简单选择排序
     * 
     * 简单选择排序是最简单直观的一种算法，基本思想为每一趟从待排序的数据元素中选择最小（或最大）的一个元素作为首元素，
     * 直到所有元素排完为止，简单选择排序是不稳定排序。
     * 时间复杂度为O(n2)
     * @param arr
     */
    public void selectSort(int[] arr){
        for (int i = 0; i < arr.length-1; i++) {
            int min = i;  //每一趟循环比较时，min用于存放较小元素的数组下标，这样当前批次比较完毕最终存放的就是此趟内最小的元素的下标，避免每次遇到较小元素都要进行交换。
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j] < arr[min]){
                    min = j;
                }
            }
            if (min != i){
                swap(arr, i, min);
            }
        }
    }

    /**
     * 冒泡排序
     * 基本思想是，对相邻的元素进行两两比较，顺序相反则进行交换，
     * 这样，每一趟会将最小或最大的元素“浮”到顶端，最终达到完全有序
     * @param arr
     */
    public void bubbleSort(int[] arr){
        for (int i = 0; i < arr.length-1; i++) {
            boolean flag = false;   //设定一个标记，若为true，则表示此次循环没有进行交换，也就是待排序列已经有序，排序已然完成。
            for (int j = 0; j < arr.length-1-i; j++) {
                if (arr[j] > arr[j+1]){
                    swap(arr, j, j+1);
                    flag = true;
                }
            }
            if (flag){
                break;
            }
        }
    }
    
    public void insertSort(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            for (; j >0 && temp<arr[j-1] ; j--) {
                arr[j] = arr[j-1];
            }
            arr[j] = temp;
        }
        
    }

    public void swap(int[] arr, int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }


    public static void main(String[] args) {
        int[] a = {3,1,5,7,2,4,9,6,10,8};
        SampleSort ss = new SampleSort();
//        ss.selectSort(a);
        ss.insertSort(a);
        System.out.println(Arrays.toString(a));
    }
    
}
