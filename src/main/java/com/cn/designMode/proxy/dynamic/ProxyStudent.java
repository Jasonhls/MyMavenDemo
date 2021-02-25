package com.cn.designMode.proxy.dynamic;

public class ProxyStudent implements ProxyPerson {
    @Override
    public void eat() {
        System.out.println("学生吃饭");
    }

    @Override
    public String walk(String str) {
        System.out.println("学生跑步");
        return "学生跑步了" + str;
    }
}
