package com.cn.threadAndLock.threadPool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Jason on 2019/3/1.
 * 自定义自己的阻塞线程池
 *
 */
public class MyBlockingThreadPoolExecutor {
    private ThreadPoolExecutor pool = null;


    /**
     * 线程池初始化方法
     *
     * corePoolSize 核心线程池大小----1
     * maximumPoolSize 最大线程池大小----3
     * keepAliveTime 线程池中超过corePoolSize数目的空闲线程最大存活时间----30+单位TimeUnit
     * TimeUnit keepAliveTime时间单位----TimeUnit.MINUTES
     * workQueue 阻塞队列----new ArrayBlockingQueue<Runnable>(5)====5容量的阻塞队列
     * threadFactory 新建线程工厂----new CustomThreadFactory()====定制的线程工厂
     * rejectedExecutionHandler 当提交任务数超过maxmumPoolSize+workQueue之和时,
     *                          即当提交第41个任务时(前面线程都没有执行完,此测试方法中用sleep(100)),
     *                                任务会交给RejectedExecutionHandler来处理
     */
    public void init() {
        pool = new ThreadPoolExecutor(
                1,
                3,
                30,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<Runnable>(5),
                new MyBlockingThreadFactory(),
                new MyBlockingRejectedExecutionHandler());
    }


    public void destory() {
        if(pool != null) {
            pool.shutdownNow();
        }
    }


    public ExecutorService getCustomThreadPoolExecutor() {
        return this.pool;
    }

    private class MyBlockingThreadFactory implements ThreadFactory {

        private AtomicInteger count = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            String threadName = MyBlockingThreadPoolExecutor.class.getSimpleName() + count.addAndGet(1);
            System.out.println("新增了线程：" + threadName);
            t.setName(threadName);
            return t;
        }

    }

    private class MyBlockingRejectedExecutionHandler implements RejectedExecutionHandler{
        Lock lock = new ReentrantLock();
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            lock.lock();
            try {
                System.out.println("1阻塞队列的大小值：" + executor.getQueue().size());
                executor.getQueue().put(r);//executor.getQueue()得到的是就是当初构造放进去的那个ArrayBlockingQueue,阻塞队列，存放多余的任务
                System.out.println("2阻塞队列的大小值：" + executor.getQueue().size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }

    /**
     *
     解释：当提交任务被拒绝时，进入拒绝机制，
     我们实现拒绝方法，把任务重新用阻塞提交方法put提交，
     实现阻塞提交任务功能，防止队列过大，OOM，提交被拒绝方法在下面
     * @param args
     */
    public static void main(String[] args){
        MyBlockingThreadPoolExecutor executor = new MyBlockingThreadPoolExecutor();
        executor.init();
        ExecutorService es = executor.getCustomThreadPoolExecutor();
        for (int i = 0;i < 10;i++){
            System.out.println(Thread.currentThread().getName() + "开始提交第" + i + "个任务!");
            es.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(10);
                        System.out.println("当前线程"+Thread.currentThread().getName() + "正在执行 task=====");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            System.out.println(Thread.currentThread().getName() + "提交完了第" + i + "个任务!");
        }
    }
}
