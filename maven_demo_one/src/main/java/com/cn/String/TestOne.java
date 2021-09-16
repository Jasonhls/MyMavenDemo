package com.cn.String;

public class TestOne {
    public static void main(String[] args) {
        Example example;
        System.out.println(Example.a);
        example =new Example();
        example.a = "c";
        System.out.println(example.a);
//        example.change(example.str,example.ch,example.a,example.i);
        example = example.change2(example);
        System.out.println(example.a);
        System.out.println(example.i);
        System.out.println(example.str);
        System.out.println(example.ch);
    }
}
