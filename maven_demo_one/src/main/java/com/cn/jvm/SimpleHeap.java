package com.cn.jvm;

/**
 * @description:
 * @author: helisen
 * @create: 2021-05-08 18:30
 *
 * VM Options设置为：-Xms10m -Xmx10m -XX:+PrintGCDetails
 **/
public class SimpleHeap {
        private int id;

    public SimpleHeap(int id) {
        this.id = id;
    }

    public void show() {
        System.out.println("My ID is " + id);
    }

    public static void main(String[] args) {
        SimpleHeap s1 = new SimpleHeap(1);
        SimpleHeap s2 = new SimpleHeap(2);

        s1.show();
        s2.show();
    }
}
