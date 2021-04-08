package com.cn.threadAndLock.JUC.reentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: helisen
 * @create: 2021-04-08 15:44
 **/
public class ReentrantLockTest4 {
    static final Object room = new Object();
    static boolean hasCigarette = false;
    static boolean hasTakeout = false;
    private static ReentrantLock ROOM = new ReentrantLock();
    //等烟的休息室
    static Condition waitCigaretteSet = ROOM.newCondition();
    //等外卖的休息室
    static Condition waitTakeoutSet = ROOM.newCondition();

    public static void main(String[] args) throws InterruptedException {
       Thread t1 = new Thread(() -> {
           ROOM.lock();
           try {
               System.out.println(Thread.currentThread().getName() + "有烟没？" + hasCigarette);
               while(!hasCigarette) {
                   System.out.println(Thread.currentThread().getName() + "没有烟，先歇会");
                   try {
                       waitCigaretteSet.await();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
               System.out.println(Thread.currentThread().getName() + "有烟了，可以开始干活了");
           }finally {
               ROOM.unlock();
           }
       }, "t1");
       t1.start();

        Thread t2 = new Thread(() -> {
            ROOM.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "外卖送到没？" + hasCigarette);
                while(!hasTakeout) {
                    System.out.println(Thread.currentThread().getName() + "没外卖，先歇会");
                    try {
                        waitTakeoutSet.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + "有外卖了，可以开始干活了");
            }finally {
                ROOM.unlock();
            }
        }, "t2");
        t2.start();


        Thread.sleep(1000);

        new Thread(() -> {
            ROOM.lock();
            try {
                hasCigarette = true;
                waitCigaretteSet.signal();
            }finally {
                ROOM.unlock();
            }
        }, "送烟的").start();

        Thread.sleep(1000);

        new Thread(() -> {
            ROOM.lock();
            try {
                hasTakeout = true;
                waitTakeoutSet.signal();
            }finally {
                ROOM.unlock();
            }
        }, "送外卖的").start();

    }
}
