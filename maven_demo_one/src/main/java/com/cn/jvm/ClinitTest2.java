package com.cn.jvm;

/**
 * @description:
 * @author: helisen
 * @create: 2021-05-07 14:20
 **/
public class ClinitTest2 {
    /**
     * 任何一个类声明以后，内部至少存在一个类的构造器
     */
    private int a = 1;

    private static int c = 3;

    public static void main(String[] args) {
        int b = 2;
    }

    public ClinitTest2() {
        a = 10;
        int d = 20;
    }
}
