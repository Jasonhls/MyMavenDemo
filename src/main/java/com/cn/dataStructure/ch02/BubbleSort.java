package com.cn.dataStructure.ch02;

/**
 * Created by Jason on 2018/6/14.
 */
public class BubbleSort {

    public static void sort(long[] arr){
        long tmp = 0;
        for (int i = 0;i < arr.length - 1;i++){
            for (int j = arr.length - 1;j > i;j--){
                if(arr[j] < arr[i]){
                    tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
    }
}
