package com.cn.threadAndLock.lock.ReentrantLock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Jason on 2019/2/28.
 */
public class FairAndUnfairTest {
    private static CountDownLatch latch;

    private static class MyReentrantLock extends ReentrantLock{
        public MyReentrantLock(boolean fair) {
            super(fair);
        }

        public Collection<Thread> getQueuedThreads(){
            List<Thread> threadList = new ArrayList<>(super.getQueuedThreads());
            Collections.reverse(threadList);
            return threadList;
        }
    }

    private static class Worker extends Thread{
        private Lock lock;

        public Worker(Lock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0;i < 2;i++){
                lock.lock();
                try {
                    System.out.println("Lock by [" + getName() + "[,Waiting by "
                            + ((MyReentrantLock)lock).getQueuedThreads());
                } finally {
                    lock.unlock();
                }
            }
        }

        public String toString(){
            return "标记-" + getName();
        }
    }

    public static void main(String[] args){
        Lock fairLock = new MyReentrantLock(true);
        Lock unfairLock = new MyReentrantLock(false);

        testLock(fairLock);
        testLock(unfairLock);
    }

    private static void testLock(Lock lock){
        latch = new CountDownLatch(1);
        for (int i = 0;i < 5;i++){
            Thread thread = new Worker(lock);
            thread.setName("线程名" + i);
            thread.start();
        }
        latch.countDown();
    }

}
