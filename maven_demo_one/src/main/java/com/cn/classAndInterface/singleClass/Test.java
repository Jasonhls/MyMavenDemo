package com.cn.classAndInterface.singleClass;

public class Test {
    public static void main(String[] args) {
        //用到了SingleTon，初始化的时候，先执行静态属性
        SingleTon singleTon = SingleTon.getInstance();
        System.out.println("count1 = " + singleTon.count1);
        System.out.println("count2 = " + singleTon.count2);
    }
}
