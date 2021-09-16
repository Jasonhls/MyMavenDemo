package com.cn.thread;

import com.cn.utils.SleepUtils;

/**
 * @description:
 * @author: helisen
 * @create: 2020-05-27 16:42
 **/
public class Interrupted {
    public static void main(String[] args) {
        Thread sleepThread = new Thread(new SleepRunner(), "sleepRunner");
        Thread busyThread = new Thread(new BusyRunner(), "busyRunner");
        sleepThread.start();
        busyThread.start();
        SleepUtils.second(20);

        sleepThread.interrupt();
        busyThread.interrupt();
        System.out.println("SleepThread interrupted is " + sleepThread.isInterrupted());
        System.out.println("BusyThread interrupted is " + busyThread.isInterrupted());

        SleepUtils.second(10);
    }

    static class SleepRunner implements Runnable {
        @Override
        public void run() {
            while(true) {
                SleepUtils.second(10);
            }
        }
    }

    static class BusyRunner implements Runnable {
        @Override
        public void run() {
            while(true){

            }
        }
    }


}
