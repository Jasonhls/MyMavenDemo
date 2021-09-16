package com.cn.jvm;

/**
 * @description:
 * @author: helisen
 * @create: 2021-05-12 11:30
 **/
public class StringIntern2 {
    public static void main(String[] args) {
//        String s1 = new String("ab");//执行完以后，会在字符串常量池中生成 "ab"
        String s1 = new String("a") + new String("b");//执行完以后，不会在常量池中生成"ab"
        s1.intern();
        String s2 = "ab";
        System.out.println(s1 == s2);

    }
}
