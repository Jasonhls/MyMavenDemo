package com.cn.dataStructure.ch01;

/**
 * Created by Jason on 2018/6/14.
 */
public class TestArray {
    public static void main(String[] args){
//        MyArray myArray = new MyArray();
//        myArray.insert(20);
//        myArray.insert(34);
//        myArray.insert(45);
//        myArray.display();
//
//        System.out.println(myArray.search(34));
//        System.out.println(myArray.get(2));

//        myArray.delete(0);
//        myArray.display();
//        myArray.change(0,10);
//        myArray.display();

        MyOrderArray myOrderArray = new MyOrderArray();
        myOrderArray.insert(90);
        myOrderArray.insert(40);
        myOrderArray.insert(30);
        myOrderArray.insert(80);
        myOrderArray.display();

        System.out.println(myOrderArray.binarySearch(20));
    }
}
