package com.cn.classAndInterface;

public class SSClass {
    static int a = 1;

    static {
        System.out.println("SSClass static ...");
    }

    public SSClass(){
        System.out.println("ssh构造方法");
    }
}
