package com.cn.dataStructure.hashtable;

public class Node {
    //数据
    public Info info;
    //指针域
    public Node next;

    public Node(Info info){
        this.info = info;
    }

    //显示方法
    public void display(){
        System.out.println(info.toString() + " ");
    }
}
