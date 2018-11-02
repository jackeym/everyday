package com.one.algorithm.sort;

import java.util.Arrays;

/**
 * 快速排序
 * 
 * 基本思想：
 * 1）选择一个基准元素,通常选择第一个元素或者最后一个元素,
 * 2）通过一趟排序讲待排序的记录分割成独立的两部分，其中一部分记录的元素值均比基准元素值小。另一部分记录的 元素值比基准值大。
 * 3）此时基准元素在其排好序后的正确位置
 * 4）然后分别对这两部分记录用同样的方法继续进行排序，直到整个序列有序。
 * @author: one
 */
public class QuickSort {
    
    public void quickSort(int[] a, int low, int high){
        if (low < high){
            int mid = getMiddle(a, low, high);
            quickSort(a, 0, mid-1);
            quickSort(a, mid+1, high);
        }
    }
    
    public int getMiddle(int[] a, int low, int high){
        int key = a[low];   //基准元素，排序中会空出来一个位置
        
        while (low<high){
            while (low<high && a[high]>=key){   //从high开始找比基准小的，与low换位
                high--;
            }
            a[low] = a[high];
            while (low<high && a[low]<=key){    //从low开始找比基准大,放到之前high空出来的位置上
                low++;
            }
            a[high] = a[low];
        }
        
        a[low] = key;   //此时low=high 是基准元素的位置，也是空出来的那个位置 
        return low;
    }

    public static void main(String[] args) {
        int[] a = {3,1,5,7,2,4,9,6,10,8};
        int h=a.length-1;
        QuickSort qs = new QuickSort();
        qs.quickSort(a, 0, h);
        System.out.println(Arrays.toString(a));
    }
}
