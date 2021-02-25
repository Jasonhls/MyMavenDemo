package com.cn.classAndInterface;

public class SubClass extends SuperClass{
    static int c = 3;

    static {
        System.out.println("subClass static ...");
    }

    public SubClass(){
        System.out.println("subClass的构造方法");
    }
}
