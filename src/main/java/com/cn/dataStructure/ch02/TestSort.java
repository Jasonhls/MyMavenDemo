package com.cn.dataStructure.ch02;

/**
 * Created by Jason on 2018/6/14.
 */
public class TestSort {
    public static void main(String[] args){
        long[] arr = new long[5];
        arr[0] = 29;
        arr[1] = 34;
        arr[2] = 2;
        arr[3] = -5;
        arr[4] = 100;
        display(arr);
//        BubbleSort.sort(arr);
//        SelectionSort.sort(arr);
        InsertSort.sort(arr);
        display(arr);
    }

    public static void display(long[] arr){
        System.out.print("[");
        for (int i = 0;i < arr.length;i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println("]");
    }
}
