package com.cn.jvm;

/**
 * @description:
 * String垃圾回收：
 *      -Xms15m -Xmx15m -XX:+PrintStringTableStatistics -XX:+PrintGCDetails
 * @author: helisen
 * @create: 2021-05-12 14:26
 **/
public class StringGCTest {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            String.valueOf(i).intern();
        }
    }
}
