package com.cn.threadAndLock.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

public class ConcurrentHashMapTest {
    public static void main(String[] args) {
//        Map<String, Integer> map =  new ConcurrentHashMap<String, Integer>();
        Map<String, LongAdder> map =  new ConcurrentHashMap<String, LongAdder>();
        List<String> words = new ArrayList<>();
        for (String word : words) {
            //虽然map是线程安全的，但是多个线程安全的方法组合起来并不是线程安全的，
            // 因此下面这三行代码在多线程情况下有问题
            /*Integer counter = map.get(word);
            int newValue = counter == null ? 1 : counter + 1;
            map.put(word, newValue);*/

            //改进如下：
            LongAdder value = map.computeIfAbsent(word, (key) -> new LongAdder());
            //执行累加
            value.increment();
        }
    }


}
