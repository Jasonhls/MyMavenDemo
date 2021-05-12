package com.cn.jvm;

import org.junit.Test;

/**
 * @description:
 * @author: helisen
 * @create: 2021-05-12 09:57
 **/
public class StringTest5 {
    @Test
    public void test1() {
        String s1 = "a" + "b" + "c";//等同于"abc"
        String s2 = "abc";//"abc"一定是放在字符串常量池中，将此地址赋给s2

        /**
         * 最终.java编译成的.class内容如下：
         * String s1 = "abc";
         * String s2 = "abc";
         */
        System.out.println(s1 == s2);//true
        System.out.println(s1.equals(s2));//true
    }

    @Test
    public void test2() {
        String s1 = "javaEE";
        String s2 = "hadoop";

        String s3 = "javaEEhadoop";
        String s4 = "javaEE" + "hadoop";//编译期优化
        //如果拼接符号的前后出现了变量，则相当于在堆空间中new String()，具体的内容为拼接的结果：javaEEhadoop
        String s5 = s1 + "hadoop";
        String s6 = "javaEE" + s2;
        String s7 = s1 + s2;

        System.out.println(s3 == s4);//true
        System.out.println(s3 == s5);//false
        System.out.println(s3 == s6);//false
        System.out.println(s3 == s7);//false
        System.out.println(s5 == s6);//false
        System.out.println(s5 == s7);//false
        System.out.println(s6 == s7);//false

        //intern()：该方法就是判断字符串常量池中是否存在javaEEhadoop值，如果存在，则返回字符串常量池中javaEEhadoop的地址，
        //如果字符串常量池中不存在javaEEhadoop，则在常量池中加载一份javaEEhadoop，并返回此对象的地址。
        String s8 = s6.intern();
        System.out.println(s3 == s8);//true
    }

    @Test
    public void test3() {
        String s1 = "a";
        String s2 = "b";
        String s3 = "c";
        /**
         * 如下的s1 + s2 的执行细节：（变量s是我临时定义的）
         *  1.StringBuilder s = new StringBuilder();
         *   2.s.append("b")
         *   3.s.append("b")
         *   4.s.toString() --->约等于 new String("ab");
         *
         *   补充：在jdk5.0之后使用的是StringBuilder，在jdk5.0之前使用的是StringBuffer。
         */
        String s4 = s1 + s2;
        System.out.println(s3 == s4);//false
    }

    /**
     * 1. 字符串拼接操作不一定使用的是StringBuilder，
     *      如果拼接符号左右两边都是字符串常量或常量引用，则仍然使用编译期优化，即非StringBuilder的方式。
     *  2.针对final修饰类、方法、基本数据类型、引用数据类型的量的结构时，能使用上final的时候建议使用上。
     */
    @Test
    public void test4() {
        final String s1 = "a";
        final String s2 = "b";
        String s3 = "ab";
        String s4 = s1 + s2;
        System.out.println(s3 == s4);//true
    }

    @Test
    public void test5() {
        String s1 = "javaEEhadoop";
        String s2 = "javaEE";
        String s3 = s2 + "hadoop";
        System.out.println(s1 == s3);//false

        final String s4 = "javaEE";//s4为常量
        String s5 = s4 + "hadoop";
        System.out.println(s1 == s5);//true
    }

    /**
     * 体会执行效率：通过StringBuilder的append()的方式添加字符串的效率要远高于String的字符串拼接方式。
     * 详情：
     *  1.StringBuilder的append()的方式：自始自终只创建过一个StringBuilder的对象。
     *      使用String的字符串拼接方式：创建过多个StringBuilder和String的对象。
     *  2.使用String字符串拼接方式：内存中由于创建较多的StringBuilder和String对象，内存占用更大；如果进行GC，需要花费额外的时间。
     *  改进的空间：在实际开发中，如果基本确定要前前后后添加的字符串长度不高于某个限定值highLevel的情况，建议使用如下构造器实例化。
     *  StringBuilder s = new StringBuilder(highLevel);//new char[highLevel]
     */
    @Test
    public void test6() {
        long start = System.currentTimeMillis();
//        method1(100000);//花费的时间：4173
        method2(100000);//花费的时间：3
        long end = System.currentTimeMillis();
        System.out.println("花费的时间：" + (end - start));
    }

    public void method1(int highLevel) {
        String src = "";
        for (int i = 0; i < highLevel; i++) {
            src = src + "a";//每次循环都会创建一个StringBuilder和String
        }
    }

    public void method2(int highLevel) {
        StringBuilder src = new StringBuilder();
        for (int i = 0; i < highLevel; i++) {
            src.append("a");
        }
    }
}
