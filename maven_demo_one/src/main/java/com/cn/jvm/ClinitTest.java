package com.cn.jvm;

/**
 * @description:
 * @author: helisen
 * @create: 2021-05-07 14:08
 **/
public class ClinitTest {
    private static int num = 1;

    static {
        num = 2;
        number = 20;
        System.out.println(num);
//        System.out.println(number);//报错：非法的前向引用。
    }

    /**
     *在linking的prepare阶段：number被赋值为0，在初始化阶段：number首先被赋值为20，然后被赋值为10
     */
    private static int number = 10;

    public static void main(String[] args) {
        System.out.println(ClinitTest.num);
        System.out.println(ClinitTest.number);
    }
}
