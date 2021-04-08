package com.cn.threadAndLock.JUC.CAS;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @description:
 * @author: helisen
 * @create: 2021-04-08 17:52
 **/
public class AtomicABATest {
    static AtomicReference<String> ref = new AtomicReference<>("A");

    public static void main(String[] args) throws InterruptedException {
        System.out.println("main start");
        String prev = ref.get();
        other();
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName() + " A -> C " + ref.compareAndSet(prev, "C"));
    }

    private static void other() {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " A -> B " + ref.compareAndSet(ref.get(), "B"));
        }, "t1").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " B -> A " + ref.compareAndSet(ref.get(), "A"));
        }, "t2").start();
    }
}
