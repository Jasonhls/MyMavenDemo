package com.cn.jvm;

public class StackTest {
    static int count = 0;

    static void reno(){
        count++;
        reno();
    }

    public static void main(String[] args) {
        reno();
    }
}
