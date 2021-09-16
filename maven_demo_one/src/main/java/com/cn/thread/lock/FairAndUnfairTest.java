package com.cn.thread.lock;

import com.cn.utils.SleepUtils;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: helisen
 * @create: 2020-05-28 11:03
 **/
public class FairAndUnfairTest {

    private static Lock fairLock = new ReentrantLock2(true);
    private static Lock unfairLock = new ReentrantLock2(false);

    @Test
    public void fair() {
        testLock(fairLock);
    }

    @Test
    public void unfair() {
        testLock(unfairLock);
    }

    private void testLock(Lock lock) {
        int count = 5;
        for (int i = 0; i < count; i++) {
            Thread job = new Job(lock);
            job.setDaemon(true);
            job.start();
        }
        SleepUtils.second(1);
    }

    private static class Job extends Thread {
        private Lock lock;

        public Job(Lock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                System.out.print("Locked by " + Thread.currentThread().getName());
                System.out.print("，Waiting by ");
                ReentrantLock2 reentrantLock2 = (ReentrantLock2)lock;
                List<Thread> queuedThreads = reentrantLock2.getQueuedThreads();
                if(queuedThreads.size() > 0){
                    for (int i = 0; i < queuedThreads.size(); i++) {
                        Thread thread = queuedThreads.get(i);
                        System.out.print(thread.getName() + " ");
                    }
                }
                System.out.println("");
            } finally {
                lock.unlock();
            }
        }
    }

    public static class ReentrantLock2 extends ReentrantLock {
        public ReentrantLock2(boolean fair) {
            super(fair);
        }

        @Override
        public List<Thread> getQueuedThreads() {
            List<Thread> arrayList = new ArrayList<>(super.getQueuedThreads());
            Collections.reverse(arrayList);
            return arrayList;
        }
    }
}
