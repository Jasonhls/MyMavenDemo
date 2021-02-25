package com.cn.dataStructure.ch03;

/**
 * Created by Jason on 2018/6/17.
 */
public class MyStack {
    //底层实现是一个数组
    private long[] arr;
    private int top;

    /**
     * 默认构造方法
     */
    public MyStack(){
        arr = new long[10];
        top = -1;
    }

    /**
     * 有参构造方法
     */
    public MyStack(int maxsize){
        arr = new long[maxsize];
        top = -1;
    }

    /**
     * 添加数据
     */
    public void push(long value){
        arr[++top] = value;
    }

    /**
     * 移除数据
     */
    public long pop(){
        return arr[top--];
    }

    /**
     * 查找数据
     */
    public long peek(){
        return arr[top];
    }

    /**
     * 数据是否满了
     */
    public boolean isFull(){
        return top == arr.length - 1;
    }

    /**
     * 判断是否为空
     */
    public boolean isEmpty(){
        return top == -1;
    }
}
