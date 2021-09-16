package com.cn.classAndInterface.singleClass;

public class SingleTon {
    private static SingleTon singleTon = new SingleTon();
    public static int count1;
    public static int count2 = 10;

    private SingleTon(){
        count1++;
        count2++;
    }

    static {
        count1++;
        count2++;
    }

    public static SingleTon getInstance(){
        return singleTon;
    }

}
