package com.cn.jvm;

/**
 * @description:
 * @author: helisen
 * @create: 2021-05-17 11:18
 **/
public class TestT {

    public static void main(String[] args) {
        System.out.println(a());
    }
    public static String a() {
        try {
            int i = 1 / 0;
            return "ok";
        }catch (Exception e) {
            throw new RuntimeException("hahah");
        }finally {
            return "finally";
        }
    }
}
