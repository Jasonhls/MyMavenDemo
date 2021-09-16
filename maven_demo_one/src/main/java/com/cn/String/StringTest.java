package com.cn.String;

/**
 * Java中的String是不可变
 */
public class StringTest {
    public static void main(String[] args) {
        String ss = "123456";
        System.out.println("ss=" + ss);
        ss.replaceAll("1","h");
        System.out.println("ss=" + ss);
        String s = "123";
        ss.equals(s);
        ss.hashCode();
        Integer i = 123;
        i.hashCode();
    }
}
