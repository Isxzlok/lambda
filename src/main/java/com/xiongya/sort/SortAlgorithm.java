package com.xiongya.sort;

import java.util.Arrays;

/**
 * @description:java常见排序算法
 * @author: xiongzhilong
 * @create: 2020-07-24 16:14
 */
public class SortAlgorithm {

    static int[] arr = new int[]{
            5, 2, 9, 6, 1, 4, 8
    };

    /**
     * 冒泡排序 （每相邻两个比较）
     * 时间复杂度 最佳 O(n) 最坏O(n2)
     * @param arr 数组
     * @return
     */
    public static int[] bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        return arr;
    }


    /**
     * 选择排序
     * @param arr
     * @return
     */
    public static int[] selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
                if (minIndex != i) {
                    int temp = arr[i];
                    arr[i] = arr[minIndex];
                    arr[minIndex] = temp;
                }
            }
        }
        return arr;
    }

    /**
     * 插入排序
     * @param arr
     * @return
     */
    public static int[] insertSort(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (arr[j] < arr[j-1]) {
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                } else {
                    break;
                }
            }
        }
        return arr;
    }

//    /**
//     * 希尔排序
//     * @param arr
//     * @return
//     */
//    public static int[] shellSort(int[] arr) {
//
//    }

    /**
     * 快速排序
     * @param arr
     * @param leftIndex
     * @param rightIndex
     */
    public static void quickSort(int[] arr, int leftIndex, int rightIndex) {
        if (leftIndex > rightIndex) {
            return;
        }
        int left = leftIndex;
        int right = rightIndex;
        //基准值
        int key = arr[leftIndex];
        while (rightIndex > leftIndex) {
            while (leftIndex < rightIndex && arr[rightIndex] >= key) {
                //从右往左，直到找到比基准值小的
                rightIndex --;
            }
            arr[leftIndex] = arr[rightIndex];
            while (leftIndex < rightIndex && arr[leftIndex] <= key) {
                //从左往右，直到找到比基准值大的
                leftIndex ++;
            }
            arr[rightIndex] = arr[leftIndex];
        }
        //替换基准值
        arr[leftIndex] = key;
        //递归
        quickSort(arr, left, leftIndex - 1);
        quickSort(arr, leftIndex + 1, right);
    }



    public static void main(String[] args) {
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
//        System.out.println(Arrays.toString(bubbleSort(arr)));
//        System.out.println(Arrays.toString(selectionSort(arr)));
//        StringBuffer stringBuffer = new StringBuffer();
//        stringBuffer.append("11");
//        System.out.println(stringBuffer.hashCode());
//        stringBuffer.append("222");
//        System.out.println(stringBuffer.hashCode());
    }


}
