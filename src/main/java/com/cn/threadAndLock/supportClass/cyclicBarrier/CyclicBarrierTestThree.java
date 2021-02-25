package com.cn.threadAndLock.supportClass.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by Jason on 2019/2/26.
 * 下面的代码在main方法的for循环中，故意让最后一个线程启动延迟，因为在前面三个线程都达到barrier之后，
 * 等待了指定的时间发现第四个线程还没有达到barrier，就抛出异常并继续执行后面的任务
 */
public class CyclicBarrierTestThree {
    public static void main(String[] args){
        int N = 4;
        CyclicBarrier barrier = new CyclicBarrier(N);

        for (int i = 0;i < N;i++){
            if(i < N - 1){
                new Writer(barrier).start();
            }else{
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new Writer(barrier).start();
            }
        }
    }

    static class Writer extends Thread{
        private CyclicBarrier barrier;

        public Writer(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "正在写入数据");
            try {
                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getName() + "写入数据完毕，等待其他线程写入完");
                barrier.await(3000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            System.out.println("所有线程写入完毕，继续执行其他任务");
        }
    }
}
