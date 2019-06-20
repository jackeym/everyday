package com.one.algorithm.sort;

import java.util.Arrays;

/**
 * 堆排序
 * @author: one
 */
public class HeapSort {

    public static void sort2(int[] arr) {
        //1.构建大顶堆
        int n = arr.length - 1;
        for (int i = (n- 1) / 2 ; i >= 0; i--) {
            //从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(arr, i, n);
        }
        //2.调整堆结构+交换堆顶元素与末尾元素
        while ( n > 0) {
            swap(arr, 0, n--);//将堆顶元素与末尾元素进行交换
            adjustHeap(arr, 0, n);//重新对堆进行调整
        }

    }

    /**
     * 调整大顶堆（下沉）
     *
     * @param arr
     * @param k
     * @param n
     */
    public static void adjustHeap(int[] arr, int k, int n) {
        while (true){
            int maxPos = k;     //最大元素下标
            int left = 2 * k + 1;   //左节点
            if (left <= n && arr[maxPos] < arr[left]) maxPos = left;    // 左节点大的话
            if (left+1 <= n && arr[maxPos] < arr[left+1]) maxPos = left+1;  //右节点存在且大的话
            if (maxPos == k) break; 

            swap(arr, k, maxPos);
            k = maxPos;
            
        }
        
    }

    public static void sort1(int[] arr) {
        int arrayLength = arr.length;
        //从左至右循环建堆 
        for (int i = 0; i < arrayLength - 1; i++) {
            //建堆 
            buildMaxHeap(arr, arrayLength - 1 - i);
            //交换堆顶和最后一个元素 
            swap(arr, 0, arrayLength - 1 - i);
        }
    }

    //对arr数组从0到lastIndex建大顶堆 
    private static void buildMaxHeap(int[] arr, int lastIndex) {
        // 从lastIndex处节点（最后一个节点）的父节点开始 
        for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
            //下沉操作
            adjustHeap(arr, i, lastIndex);
        }
    }

    /**
     * 交换元素
     *
     * @param arr
     * @param a
     * @param b
     */
    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {4, 6, 8, 5, 9, 1, 2, 3, 7};
        sort2(arr);
        System.out.println(Arrays.toString(arr));
    }
}
