package com.one.algorithm.sort;

import java.util.Arrays;

/**
 * 希尔排序
 * 
 * 希尔排序也是一种插入排序，它是简单插入排序经过改进之后的一个更高效的版本，也称为缩小增量排序。
 * 把记录按下标的一定增量分组，对每组使用直接插入排序算法排序；随着增量逐渐减少，每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止。
 * 
 * 基本步骤：
 * 在此我们选择增量gap=length/2，缩小增量继续以gap = gap/2的方式，分成若干组子序列，每组中记录的下标相差gap.
 * 继续不断缩小增量直至为1，最后使用直接插入排序完成排序。
 * @author: one
 */
public class ShellSort {
    
    public void sort1(int[] a){
        //增量gap，并逐步缩小增量
        for (int gap = a.length/2; gap > 0 ; gap/=2) {
            //从第gap个元素，逐个对其所在组进行直接插入排序操作
            for (int i = gap; i < a.length; i++) {
                int j = i;
                int temp = a[j];
                for (; j-gap>=0 && temp<a[j-gap]; j-=gap) {
                    //移动法
                    a[j] = a[j-gap];
                }
                a[j] = temp;
            }
        }
    }

    public void sort2(int[] a){
        //增量gap，并逐步缩小增量
        for (int gap = a.length/2; gap > 0 ; gap/=2) {
            //从第gap个元素，逐个对其所在组进行直接插入排序操作
            for (int i = gap; i < a.length; i++) {
                int j = i;
                int temp = a[j];
                for (; j-gap>=0 && temp<a[j-gap]; j-=gap) {
                    //插入排序采用交换法
                    swap(a,j,j-gap);
                }
            }
        }
    }
    
    public void swap(int[] arr, int a, int b){
        arr[a] = arr[a] + arr[b];
        arr[b] = arr[a] - arr[b];
        arr[a] = arr[a] - arr[b];
    }

    public static void swap1(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String[] args) {
        int[] a = {3,1,5,7,2,4,9,6,10,8};
        ShellSort ss = new ShellSort();
        ss.sort1(a);
        System.out.println(Arrays.toString(a));
    }
}
