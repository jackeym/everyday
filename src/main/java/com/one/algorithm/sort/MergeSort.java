package com.one.algorithm.sort;

import java.util.Arrays;

/**
 * 归并排序
 * 
 * 归并排序（MERGE-SORT）是利用归并的思想实现的排序方法，该算法采用经典的分治（divide-and-conquer）策略
 * （分治法将问题分(divide)成一些小的问题然后递归求解，而治(conquer)的阶段则将分的阶段得到的各答案"修补"在一起，即分而治之)。
 * 分阶段可以理解为就是递归拆分子序列的过程，递归深度为log2n。
 * 治阶段，我们需要将两个已经有序的子序列合并成一个有序序列。
 * 
 * 1. 申请临时数组空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列
 * 2. 设定两个指针，最初位置分别为两个已经排序序列的起始位置
 * 3. 比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置
 * 4. 重复步骤3直到某一指针达到序列尾
 * 5. 将另一序列剩下的所有元素直接复制到合并序列尾
 * 
 * @author: one
 */
public class MergeSort {
    
    public void sort(int[] a, int low, int high){
        if (low < high){
            int mid = (low + high)/2;
            sort(a, low, mid);  //左边归并排序，使得左子序列有序
            sort(a, mid+1, high);   //右边归并排序，使得右子序列有序
            merge(a, low, mid, high);   //将两个有序子数组合并操作
        }
    }
    
    public void merge(int[] a, int low, int mid, int high){
        int[] temp = new int[high-low+1];   //临时数组
        int k = 0;  //临时数组指针
        int i = low;    //左指针
        int j = mid + 1;    //又指针
        
        while (i<=mid & j<=high){
            if (a[i] < a[j]){
                temp[k++] = a[i++];
            }else {
                temp[k++] = a[j++];
            }
        }

        while (i <= mid){   //将左边剩余元素填充进temp中
            temp[k++] = a[i++];
        }
        while (j <= high){  //将右序列剩余元素填充进temp中
            temp[k++] = a[j++];
        }
        
        k = 0;
        while (low <= high){    //将temp中的元素全部拷贝到原数组中
            a[low++] = temp[k++];
        }
    }

    public static void main(String[] args) {
        int[] a = {3,1,5,7,2,4,9,6,10,8};
        int h=a.length-1;
        MergeSort ms = new MergeSort();
        ms.sort(a, 0, h);
        System.out.println(Arrays.toString(a));
    }
}
