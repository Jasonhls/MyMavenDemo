package com.cn.thread;

import com.cn.utils.SleepUtils;
import org.junit.Test;

import java.util.concurrent.locks.LockSupport;

/**
 * @description:
 * @author: helisen
 * @create: 2020-05-27 09:48
 **/
public class ThreadState2 {

    static volatile Boolean b = true;
    static Object obj = new Object();

    /**
     * 使用wait()，notifyAll()
     * 测试线程MyThread由RUNNABLE -->WAITING --> RUNNABLE
     */
    @Test
    public void testThread1(){
        Thread thread = new Thread(new MyThread(), "hlsThread");
        Thread thread2 = new Thread(new MyThread2(), "hlsThread2");
        thread.start();

        SleepUtils.second(20);
        System.out.println("111");
        b = false;
        SleepUtils.second(30);
        System.out.println("222");
        thread2.start();

        System.out.println("开始了");
        System.out.println(thread.isAlive());

    }

    /**
     * 使用LockSupport
     * 测试线程MyThread3由RUNNABLE -->WAITING --> RUNNABLE
     */
    @Test
    public void testThread2(){
        Thread thread = new Thread(new MyThread3(), "hlsThread");
        thread.start();

        SleepUtils.second(20);
        System.out.println("111");
        b = false;
        SleepUtils.second(30);
        System.out.println("222");
        b = true;
        LockSupport.unpark(thread);

        System.out.println("开始了");
        System.out.println(thread.isAlive());
        //这里必须睡眠，不然主线程关闭了，线程thread也会关闭
        SleepUtils.second(100);
    }

    @Test
    public void testThread3(){
        Thread thread4 = new Thread(new MyThread4(), "hlsThread4");
        Thread thread5 = new Thread(new MyThread5(), "hlsThread5");
        thread4.start();
        SleepUtils.second(1);
        thread5.start();

        SleepUtils.second(20);
        System.out.println("111");
        b = false;
        SleepUtils.second(30);
        System.out.println("222");
        b = true;

        System.out.println("开始了");
        System.out.println(thread4.isAlive());
        //这里必须睡眠，不然主线程关闭了，线程thread也会关闭
        SleepUtils.second(100);
    }

    static class MyThread implements Runnable {
        @Override
        public void run() {
            synchronized (obj) {
                try {
                    while (true) {
                        while (b) {

                        }
                        obj.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class MyThread2 implements Runnable {
        @Override
        public void run() {
            synchronized (obj) {
                b = true;
                obj.notifyAll();
            }
        }
    }

    static class MyThread3 implements Runnable {
        @Override
        public void run() {
            synchronized (obj) {
                try {
                    while (true) {
                        while (b) {

                        }
                        LockSupport.park();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class MyThread4 implements Runnable {
        @Override
        public void run() {
            synchronized (obj) {
                while (true) {
                    while (b) {

                    }
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class MyThread5 implements Runnable {
        @Override
        public void run() {
            synchronized (obj) {
                while(true) {
                    while (!b) {

                    }
                    try {
                        obj.notifyAll();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
