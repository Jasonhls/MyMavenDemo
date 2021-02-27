package com.cn.designMode.adapter.interfaceAdapter;

public class Client {
    public static void main(String[] args) {
        AbsAdapter absAdapter = new AbsAdapter() {
            @Override
            public void m1() {
                System.out.println("实现了m1的方法");
            }
        };
        absAdapter.m1();
    }
}
