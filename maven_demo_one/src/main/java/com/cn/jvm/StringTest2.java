package com.cn.jvm;

/**
 * @description:
 * @author: helisen
 * @create: 2021-05-18 14:03
 **/
public class StringTest2 {
    String str = new String("good");
    char[] ch = {'t','e','s','t'};

    public void change(String str, char ch[]) {
        str = "test ok";
        ch[0] = 'b';
    }

    public static void main(String[] args) {
        StringExer ex = new StringExer();
        ex.change(ex.str, ex.ch);

        System.out.println(ex.str);//good
        System.out.println(ex.ch);//best
    }
}
