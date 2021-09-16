package com.cn.thread;

import com.cn.utils.SleepUtils;

/**
 * @description:
 * @author: helisen
 * @create: 2020-05-18 11:33
 **/
public class ThreadState {
    public static void main(String[] args) {
        Thread start1 = new Thread(new TimeWaiting(), "TimeWaitingThread");
        start1.start();
        Thread start2 = new Thread(new Waiting(), "WaitingThread");
        start2.start();
        Thread start3 = new Thread(new Blocked(), "BlockedThread-1");
        start3.start();
        Thread start4 = new Thread(new Blocked(), "BlockedThread-2");
        start4.start();
        SleepUtils.second(5);
        start1.interrupt();
        System.out.println("start1:" + start1.isInterrupted());
        SleepUtils.second(5);
        start2.interrupt();
        System.out.println("start2:" + start2.isInterrupted());

    }

    static class TimeWaiting implements Runnable{
        @Override
        public void run() {
            while(true){
                SleepUtils.second(1000);
            }
        }
    }

    static class Waiting implements Runnable{
        @Override
        public void run() {
            synchronized (Waiting.class) {
                try {
                    Waiting.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Blocked implements Runnable{
        @Override
        public void run() {
            synchronized (Blocked.class) {
                while(true) {
                    SleepUtils.second(2000);
                }
            }
        }
    }
}
