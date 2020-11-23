package com.cn.utils;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: helisen
 * @create: 2020-05-18 11:35
 **/
public class SleepUtils {
    public static final void second(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
