package com.cn.String;

public class Example {
//    String str = new String("hello");
    static String a = "a";
    String str = "hello";
    int i = 0;
    char[] ch2;
    char[] ch = {'a','b'};
    static {
        a = "b";
    }

    public void change(String str,char ch[],String a,int i){
        a = "aa";//只是一个赋值动作，而且这里的a并不等于Example类中的成员变量a
        i = 9;
        str = "test ok";//只是一个赋值动作，而且这里的str并不是Example类中的成员变量str
        ch[0] = 'c';
    }

    public Example change2(Example example){
        example.a = "aa";
        example.i = 9;
        example.str = "test ok";
        example.ch[0] = 'c';
        return example;
    }
}
