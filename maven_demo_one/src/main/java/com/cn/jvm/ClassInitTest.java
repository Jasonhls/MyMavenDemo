package com.cn.jvm;

/**
 * @description:
 * @author: helisen
 * @create: 2021-05-07 12:39
 **/
public class ClassInitTest {
//    private static int num = 1;

    /*static {
        num = 2;
        number = 20;
    }*/

    /**
     *在linking的prepare中，number被赋值为0，然后再initial初始化阶段中，先被赋值为20，后来又被赋值为10。
     */
//    private static int number = 10;

    public static void main(String[] args) {
        //2
//        System.out.println(ClassInitTest.num);
        //10
//        System.out.println(ClassInitTest.number);
    }
}
