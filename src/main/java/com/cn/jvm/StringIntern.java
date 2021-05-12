package com.cn.jvm;

/**
 * @description:
 * @author: helisen
 * @create: 2021-05-12 11:30
 **/
public class StringIntern {
    public static void main(String[] args) {
        String s = new String("1");
        s.intern();//调用此方法前字符串常量池中已经存在了 "1"
        String s2 = "1";
        System.out.println(s == s2);//jdk6：false  jdk7/8：false

        String s3 = new String("1") + new String("1");//s3变量记录的地址：new String("11")
        //执行完上一行代码以后，字符串常量池中，是否存在 "11" 呢？不存在
        s3.intern();//在字符串常量池中生成 "11"。如何理解：jdk6：创建了一个新的对象 "11" ，也就是有新的地址。
                                                                                            // jdk7：此时常量池中并没有创建"11"，而是创建了一个指向堆空间中new String("11")的地址。
        String s4 = "11";//s4变量记录的地址：使用的是上一行代码执行时，在常量池中生成的 "11" 的地址。
        System.out.println(s3 == s4);//jdk6：false  jdk7/8：true
    }
}
