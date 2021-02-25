package com.cn.dataStructure.ch03;

/**
 * Created by Jason on 2018/6/18.
 */
public class TestMyStack {

    public static void main(String[] args){
        MyStack myStack = new MyStack(4);
        myStack.push(20);
        myStack.push(30);
        myStack.push(5);
        myStack.push(50);

        System.out.println(myStack.isEmpty());
        System.out.println(myStack.isFull());

        System.out.println(myStack.peek());
        System.out.println(myStack.peek());

        while(!myStack.isEmpty()){
            System.out.print(myStack.pop() + " ");
        }

        System.out.println(myStack.isEmpty());
        System.out.println(myStack.isFull());

    }
}
