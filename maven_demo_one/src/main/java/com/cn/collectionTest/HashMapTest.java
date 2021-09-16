package com.cn.collectionTest;

import java.util.HashMap;

/**
 * @description:
 * @author: helisen
 * @create: 2021-05-14 09:55
 **/
public class HashMapTest {
    public static void main(String[] args) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("1", new Object());
        map.put("1", new Object());
        System.out.println(map.toString());
    }
}
