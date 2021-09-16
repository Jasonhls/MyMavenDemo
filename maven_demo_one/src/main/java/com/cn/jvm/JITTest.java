package com.cn.jvm;

import java.util.ArrayList;

/**
 * @description:
 * @author: helisen
 * @create: 2021-05-11 16:50
 **/
public class JITTest {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add("让天下没有难学的技术");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
