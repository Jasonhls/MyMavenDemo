package com.cn.threadAndLock.threadApi.immutable;

import java.time.format.DateTimeFormatter;

/**
 * @description:
 * @author: helisen
 * @create: 2021-04-08 11:29
 **/
public class MutableTest3 {
    /**
     * 使用不可变类DateTimeFormatter
     */
    public static void main(String[] args) {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(sdf.parse("1954-04-21"));
            }).start();
        }
    }
}
