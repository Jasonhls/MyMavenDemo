package com.cn.threadAndLock.JUC.CAS;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntUnaryOperator;

/**
 * @description:
 * @author: helisen
 * @create: 2021-04-08 17:35
 **/
public class AtomicIntegerTest {

    public static void main(String[] args) {
        AtomicInteger i = new AtomicInteger(10);
        i.updateAndGet(p -> p / 2);
//        myUpdateAndGet(i, p -> p / 2);
        System.out.println(i.get());
    }

    public static void myUpdateAndGet(AtomicInteger i, IntUnaryOperator operator) {
        while(true) {
            int prev = i.get();
            int next = operator.applyAsInt(prev);
            if(i.compareAndSet(prev, next)) {
                break;
            }
        }
    }
}
