package com.cn.threadAndLock.lock.reentrantReadWriteLock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读读可以并发进行
 * 读写不能并发进行，必须等读锁释放了，写锁才能获取成功
 * 写写也是不能并发进行的，必须等到前面的写锁释放了，后面的写锁才能获取成功。
 */

@Slf4j(topic = "c.TestOne")
public class TestReentrantReadWriteLock {
    public static void main(String[] args) {
        DataContainer dataContainer = new DataContainer();
        new Thread(() -> {
            dataContainer.write();
        }, "t1").start();

        new Thread(() -> {
            dataContainer.write();
        }, "t2").start();
    }
}

@Slf4j(topic = "c.DataContainer")
class DataContainer {
    private Object data;
    private ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock r = rw.readLock();
    private ReentrantReadWriteLock.WriteLock w = rw.writeLock();

    public Object read() {
        System.out.println(Thread.currentThread().getName() + ", 获取读锁");
        r.lock();
        try {
            System.out.println(Thread.currentThread().getName() + ", 获取");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return data;
        }finally {
            System.out.println(Thread.currentThread().getName() + ", 释放读锁");
            r.unlock();
        }
    }

    public void write() {
        System.out.println(Thread.currentThread().getName() + ", 获取写锁");
        w.lock();
        try {
            System.out.println(Thread.currentThread().getName() + ", 写入");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }finally {
            System.out.println(Thread.currentThread().getName() + ", 释放写锁");
            w.unlock();
        }
    }
}
