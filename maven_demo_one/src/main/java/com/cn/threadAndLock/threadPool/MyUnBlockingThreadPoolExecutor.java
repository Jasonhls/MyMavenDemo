package com.cn.threadAndLock.threadPool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Jason on 2019/3/1.
 * //自定义自己的非阻塞线程池
 */
public class MyUnBlockingThreadPoolExecutor {
    private ThreadPoolExecutor pool = null;


    /**
     * 线程池初始化方法
     *
     * corePoolSize 核心线程池大小----10
     * maximumPoolSize 最大线程池大小----30
     * keepAliveTime 线程池中超过corePoolSize数目的空闲线程最大存活时间----30+单位TimeUnit
     * TimeUnit keepAliveTime时间单位----TimeUnit.MINUTES
     * workQueue 阻塞队列----new ArrayBlockingQueue<Runnable>(10)====10容量的阻塞队列
     * threadFactory 新建线程工厂----new CustomThreadFactory()====定制的线程工厂
     * rejectedExecutionHandler 当提交任务数超过maxmumPoolSize+workQueue之和时,
     * 							即当提交第41个任务时(前面线程都没有执行完,此测试方法中用sleep(100)),
     * 						          任务会交给RejectedExecutionHandler来处理
     */
    public void init() {
        pool = new ThreadPoolExecutor(
                10,
                30,
                30,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<Runnable>(10),
                new MyThreadFactory(),
                new MyRejectedExecutionHandler());
    }

    public void destroy(){
        if(pool != null){
            pool.shutdown();
        }
    }

    public ExecutorService getMyThreadPoolExecutor(){
        return this.pool;
    }

    private class MyThreadFactory implements ThreadFactory{
        private AtomicInteger count = new AtomicInteger(0);
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            String threadName = MyThreadFactory.class.getSimpleName() + count.addAndGet(1);
            System.out.println("新建的线程：" + threadName);
            t.setName(threadName);
            return t;
        }
    }

    private class MyRejectedExecutionHandler implements RejectedExecutionHandler{
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            //记录异常
            //报警处理等
            System.out.println("阻塞队列的大小值：" + executor.getQueue().size());
            System.out.println("error......");
        }
    }

//    注意下面1处的execute方法：41以后提交的任务就不能正常处理了，因为，execute中提交到任务队列是用的offer方法，
// 如上面代码，这个方法是非阻塞的，所以就会交给CustomRejectedExecutionHandler 来处理，
//    所以对于大数据量的任务来说，这种线程池，如果不设置队列长度会OOM，设置队列长度，
// 会有任务得不到处理，接下来我们构建一个阻塞的自定义线程池
    public static void main(String[] args){
        MyUnBlockingThreadPoolExecutor exec = new MyUnBlockingThreadPoolExecutor();
        //初始化
        exec.init();

        ExecutorService es = exec.getMyThreadPoolExecutor();
        for (int i = 1;i < 100;i++){
            System.out.println("提交第" + i + "个任务！");
            es.execute(new Runnable() {//1
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("running=====");
                }
            });
        }
    }
}
