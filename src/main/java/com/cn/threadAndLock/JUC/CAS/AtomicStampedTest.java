package com.cn.threadAndLock.JUC.CAS;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @description:
 * @author: helisen
 * @create: 2021-04-08 17:52
 **/
public class AtomicStampedTest {
    static AtomicStampedReference<String> ref = new AtomicStampedReference<>("A", 0);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("main start");
        //获取值
        String prev = ref.getReference();
        //获取版本号
        int stamp = ref.getStamp();
        System.out.println("版本号为" + stamp);
        other();
        Thread.sleep(1000);
        System.out.println("版本号为" + ref.getStamp());
        System.out.println(Thread.currentThread().getName() + " A -> C " + ref.compareAndSet(prev, "C", stamp, stamp + 1));
    }

    private static void other() {
        new Thread(() -> {
            int stamp = ref.getStamp();
            System.out.println(Thread.currentThread().getName() + " A -> B " + ref.compareAndSet(ref.getReference(), "B", stamp, stamp + 1) + " 版本号为" + ref.getStamp());
        }, "t1").start();

        new Thread(() -> {
            int stamp = ref.getStamp();
            System.out.println(Thread.currentThread().getName() + " B -> A " + ref.compareAndSet(ref.getReference(), "A", stamp, stamp + 1) + " 版本号为" + ref.getStamp());
        }, "t2").start();
    }
}
