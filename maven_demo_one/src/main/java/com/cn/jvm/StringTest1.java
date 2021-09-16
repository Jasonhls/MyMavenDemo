package com.cn.jvm;

import org.junit.Test;

public class StringTest1 {
    @Test
    public void test1() {
        String s1 = "abc";
        String s2 = "abc";
        System.out.println(s1 == s2);//true
        s1 = "hello";
        System.out.println(s1 == s2);//false
    }

    @Test
    public void test2() {
        String s1 = "abc";
        String s2 = "abc";
        s2 += "def";
        System.out.println(s2);//abcdef
        System.out.println(s1);//abc
    }

    @Test
    public void test3() {
        String s1 = "agc";
        String s2 = s1.replace('a', 'm');
        System.out.println(s1);//abc
        System.out.println(s2);//mbc

    }
}
