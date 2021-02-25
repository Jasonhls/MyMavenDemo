package com.cn.dataStructure.ch01;

/**
 * Created by Jason on 2018/6/14.
 */
public class MyArray {
    private long[] arr;

    private int elements;

    public MyArray(){
        arr = new long[50];
    }

    public MyArray(int elements){
        arr = new long[elements];
    }

    /**
     * 插入数据
     */
    public void insert(long value){
        arr[elements] = value;
        elements++;
    }

    /**
     * 显示数据
     */
    public void display(){
        System.out.print("[");
        for (int i = 0;i < elements;i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println("]");
    }
    /**
     * 查找数据，根据值去查找
     */
    public int search(long value){
        int i;
        for (i = 0;i < elements;i++){
            if(arr[i] == value){
                break;
            }
        }
        if(i == elements){
            return -1;
        }else {
            return i;
        }
    }

    /**
     * 查找数据，根据索引去查找
     */
    public long get(int index){
        if(index >= elements || index < 0){
            throw new ArrayIndexOutOfBoundsException();
        }else{
            return arr[index];
        }
    }

    /**
     * 删除数据
     */
    public void delete(int index){
        if(index >= elements || index < 0){
            throw new ArrayIndexOutOfBoundsException();
        }else{
            for (int i = 0;i < elements;i++){
                arr[index] = arr[index + 1];
            }
            elements--;
        }
    }

    /**
     * 修改数据
     */
    public void change(int index,long newValue){
        if(index >= elements || index < 0){
            throw new ArrayIndexOutOfBoundsException();
        }else{
            arr[index] = newValue;
        }
    }

}
