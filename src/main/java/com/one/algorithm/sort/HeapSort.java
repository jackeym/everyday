package com.one.algorithm.sort;

import java.util.Arrays;

/**
 * 堆排序
 * @author: one
 */
public class HeapSort {


    public static void sort1(int[] arr) {
        int arrayLength = arr.length;
        //循环建堆 
        for (int i = 0; i < arrayLength - 1; i++) {
            //建堆 
            buildMaxHeap(arr, arrayLength - 1 - i);
            //交换堆顶和最后一个元素 
            swap(arr, 0, arrayLength - 1 - i);
            System.out.println(Arrays.toString(arr));
        }
    }

    //对arr数组从0到lastIndex建大顶堆 
    private static void buildMaxHeap(int[] arr, int lastIndex) {
        // 从lastIndex处节点（最后一个节点）的父节点开始 
        for (int i = (lastIndex - 1) / 2; i >= 0; i--) { 
            //k保存正在判断的节点
            int k = i; 
            //如果当前k节点的子节点存在 
//            for (int j = k * 2 + 1; j <= lastIndex;j = k * 2 + 1){
            while (k * 2 + 1 <= lastIndex) { 
                //k节点的左子节点的索引 
                int biggerIndex = 2 * k + 1;
                //如果biggerIndex小于lastIndex，即biggerIndex+1代表的k节点的右子节点存在 
                if (biggerIndex < lastIndex) { 
                    //若果右子节点的值较大 
                    if (arr[biggerIndex] < arr[biggerIndex + 1]) { 
                        //biggerIndex总是记录较大子节点的索引 
                        biggerIndex++;
                    }
                } //如果k节点的值小于其较大的子节点的值 
                if (arr[k] < arr[biggerIndex]) { 
                    //交换他们 
                    swap(arr, k, biggerIndex); 
                    //将biggerIndex赋予k，开始while循环的下一次循环，重新保证k节点的值大于其左右子节点的值 
                    k = biggerIndex;
                } else {
                    break;
                }
            }
        }
    }


    public static void sort2(int[] arr) {
        //1.构建大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            //从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(arr, i, arr.length);
        }
        //2.调整堆结构+交换堆顶元素与末尾元素
        for (int j = arr.length - 1; j > 0; j--) {
            swap(arr, 0, j);//将堆顶元素与末尾元素进行交换
            adjustHeap(arr, 0, j);//重新对堆进行调整
        }

    }

    /**
     * 调整大顶堆（仅是调整过程，建立在大顶堆已构建的基础上）
     *
     * @param arr
     * @param i
     * @param length
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];//先取出当前元素i
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {//从i结点的左子结点开始，也就是2i+1处开始
            if (k + 1 < length && arr[k] < arr[k + 1]) {//如果左子结点小于右子结点，k指向右子结点
                k++;
            }
            if (arr[k] > temp) {//如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;//将temp值放到最终的位置
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
        sort1(arr);
        System.out.println(Arrays.toString(arr));
    }
}
