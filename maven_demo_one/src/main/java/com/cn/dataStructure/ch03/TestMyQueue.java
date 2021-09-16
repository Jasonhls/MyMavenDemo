package com.cn.dataStructure.ch03;

/**
 * Created by Jason on 2018/6/18.
 */
public class TestMyQueue {
    public static void main(String[] args){
        MyQueue myQueue = new MyQueue(4);
        myQueue.insert(30);
        myQueue.insert(45);
        myQueue.insert(12);
        myQueue.insert(2);

        System.out.println(myQueue.isEmpty());
        System.out.println(myQueue.isFull());

        System.out.println(myQueue.peek());
        System.out.println(myQueue.peek());

        while(!myQueue.isEmpty()){
            System.out.print(myQueue.remove() + " ");
        }

        System.out.println(myQueue.isEmpty());
        System.out.println(myQueue.isFull());

        myQueue.insert(3);
        myQueue.insert(4);
        myQueue.insert(1);
        myQueue.insert(5);

        System.out.println(myQueue.isEmpty());
        System.out.println(myQueue.isFull());

        while(!myQueue.isEmpty()){
            System.out.print(myQueue.remove() + " ");
        }

        System.out.println(myQueue.isEmpty());
        System.out.println(myQueue.isFull());
    }
}
