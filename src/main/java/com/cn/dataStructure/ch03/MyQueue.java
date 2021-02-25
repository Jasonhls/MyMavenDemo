package com.cn.dataStructure.ch03;

/**
 * Created by Jason on 2018/6/18.
 */
public class MyQueue {
    //底层使用数组
    private long[] arr;
    //有效数据的大小
    private int elements;
    //队头
    private int front;
    //队尾
    private int end;

    /**
     * 默认构造方法
     */
    public MyQueue(){
        arr = new long[10];
        elements = 0;
        front = 0;
        end = -1;
    }

    /**
     * 有参构造方法
     */
    public MyQueue(int maxsize){
        arr = new long[maxsize];
        elements = 0;
        front = 0;
        end = -1;
    }

    /**
     * 插入数据，从队尾添加
     */
    public void insert(long value){
        if(end == arr.length - 1){
            end = -1;
        }
        arr[++end] = value;
        elements++;
    }

    /**
     * 删除数据，从队头删除
     */
    public long remove(){
        if(front == arr.length){
            front = 0;
        }
        elements--;
        return arr[front++];
    }

    /**
     * 查找数据，从队头查找
     */
    public long peek(){
        return arr[front];
    }

    /**
     * 判断是否满了
     */
    public boolean isFull(){
        return elements == arr.length;
    }

    /**
     * 判断是否为空
     */
    public boolean isEmpty(){
        return elements == 0;
    }
}
