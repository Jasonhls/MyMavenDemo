package com.cn.threadAndLock.threadApi;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: helisen
 * @create: 2021-04-08 09:42
 **/
public class ThreadSafe {
    public void method1(int loopNumber) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < loopNumber; i++) {
            method2(list);
            method3(list);
        }
    }

    public void method2(List<String> list) {
        list.add("1");
    }

    public void method3(List<String> list) {
        list.remove(0);
    }

    class ThreadSafeSubClass extends ThreadSafe {
        @Override
        public void method3(List<String> list) {
            new Thread(() -> {
                list.remove(0);
            }).start();
        }
    }

}
