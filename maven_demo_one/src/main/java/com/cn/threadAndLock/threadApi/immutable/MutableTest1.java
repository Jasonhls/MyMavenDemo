package com.cn.threadAndLock.threadApi.immutable;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @description:
 * @author: helisen
 * @create: 2021-04-08 11:29
 **/
public class MutableTest1 {
    /**
     * 会报错
     */
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    System.out.println(sdf.parse("1954-04-21"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
