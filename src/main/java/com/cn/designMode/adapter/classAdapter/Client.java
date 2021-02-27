package com.cn.designMode.adapter.classAdapter;

public class Client {
    public static void main(String[] args) {
        System.out.println("======类适配器模式=======");
        Phone phone = new Phone();
        phone.charge(new VoltageAdapter());
    }
}
