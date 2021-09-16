package com.cn.thread.currentTools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @description:
 * @author: helisen
 * @create: 2020-06-02 11:47
 **/
public class SemaphoreTest {
    private static final int THREAD_COUNT = 30;
    private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);
    private static Semaphore s = new Semaphore(10);

    public synchronized void he() {

    }
    public static synchronized void hello() {

    }

    public static void main(String[] args) {
            for (int i = 0; i < THREAD_COUNT; i++) {
                threadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            s.acquire();
                            System.out.println("save data");
                            s.release();
                        } catch (InterruptedException e) {
                            System.out.println("lala");
                            e.printStackTrace();
                        }
                    }
                });
                threadPool.shutdown();
            }
    }
}
