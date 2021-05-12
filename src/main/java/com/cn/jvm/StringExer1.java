package com.cn.jvm;

/**
 * @description:
 * @author: helisen
 * @create: 2021-05-12 12:39
 **/
public class StringExer1 {
    public static void main(String[] args) {
        String x = "ab";
        String s = new String("a") + new String("b");//new String("ab")
        //执行完上一行代码以后，字符串常量池中并没有"ab"
        String s2 = s.intern();//jdk6：在串池中创建一个字符串"ab"
                                            //jdk7/8：串池中没有创建字符串 "ab"，而是创建一个引用，指向new String("ab")，将此引用返回。
        System.out.println(s2 == "ab");//jdk6：true  jdk7/8：true
        System.out.println(s == "ab");//jdk6：false   jdk7/8：true
    }
}
