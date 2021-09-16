package com.cn.classAndInterface;

public class SuperClass extends SSClass{
    static int b = 2;

    static {
        System.out.println("SuperClass static ...");
    }

    public SuperClass(){
        System.out.println("superClass的构造方法");
    }
}
