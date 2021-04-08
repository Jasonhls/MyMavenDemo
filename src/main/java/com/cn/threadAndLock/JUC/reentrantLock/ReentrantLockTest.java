package com.cn.threadAndLock.JUC.reentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: helisen
 * @create: 2021-04-08 15:44
 **/
public class ReentrantLockTest {
    private static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) {
        lock.lock();
        try {
            System.out.println("enter main");
            m1();
        } finally {
            lock.unlock();
        }
    }

    private static void m1() {
        lock.lock();
        try {
            System.out.println("enter m1");
            m2();
        } finally {
            lock.unlock();
        }
    }

    private static void m2() {
        lock.lock();
        try {
            System.out.println("enter m2");
        } finally {
            lock.unlock();
        }
    }
}
