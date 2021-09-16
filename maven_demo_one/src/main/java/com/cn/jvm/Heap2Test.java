package com.cn.jvm;

/**
 * @description:
 * @author: helisen
 * @create: 2021-05-08 17:43
 **/
public class Heap2Test {
    public static void main(String[] args) {
        System.out.println("start....");
        try {
            Thread.sleep(10000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end.....");
    }
}
