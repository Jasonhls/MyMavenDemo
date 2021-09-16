package com.cn.dataStructure.ch02;

/**
 * Created by Jason on 2018/6/14.
 */
public class SelectionSort {

    public static void sort(long[] arr){
        long tmp = 0;
        int k;
        for (int i = 0;i < arr.length - 1;i++){
            k = i;
            for (int j = i;j < arr.length;j++){
                if(arr[j] < arr[k]){//会一直执行下去，直到找到这趟最小的
                    k = j;
                }
            }
            tmp = arr[i];
            arr[i] = arr[k];
            arr[k] = tmp;
        }
    }
}
