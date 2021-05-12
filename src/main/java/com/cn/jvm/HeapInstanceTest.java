package com.cn.jvm;

import java.util.ArrayList;
import java.util.Random;

/**
 * @description:
 * @author: helisen
 * @create: 2021-05-10 09:59
 **/
public class HeapInstanceTest {
    byte[] buffer = new byte[new Random().nextInt(1024 * 1024)];

    public static void main(String[] args) {
        ArrayList<HeapInstanceTest> list = new ArrayList<>();
        while(true) {
            list.add(new HeapInstanceTest());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static String createStringBuffer(String s1, String s2) {
        StringBuilder sb = new StringBuilder();
        sb.append(s1);
        sb.append(s2);
        return sb.toString();
    }
}
