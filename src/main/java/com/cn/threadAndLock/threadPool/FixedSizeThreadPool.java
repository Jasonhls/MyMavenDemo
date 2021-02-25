package com.cn.threadAndLock.threadPool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 自定义线程池
 */
public class FixedSizeThreadPool {
    //需要一个仓库
    private BlockingQueue<Runnable> blockingQueue;
    //需要一个线程集合
    private List<Worker> workers;

    public static class Worker extends Thread{
        private FixedSizeThreadPool pool;

        public Worker(FixedSizeThreadPool pool) {
            this.pool = pool;
        }

        @Override
        public void run() {
            while(this.pool.isWorking || this.pool.blockingQueue.size() > 0){
                Runnable task = null;
                try {
                    if(this.pool.isWorking){
                        //如果线程池未关闭，以阻塞方式拿出任务，为啥是阻塞，可以查看源码，
                        //比如查看ArrayBlockingQueue的take()方法中，里面用到了while(count == 0)
                        //用到了死循环，不过源码中加锁用了lock.lockInterruptibly()，就是表示如果
                        //在执行过程中，for循环过程中，如果检测到interrupt标志为true，
                        // 则立刻抛出InterruptedException异常，这时程序变通过异常直接返回到最外层了，
                        // 又外层继续处理，因此使用lockInterruptibly()时必须捕捉异常
                        task = this.pool.blockingQueue.take();
                    }else{
                        //如果线程池关闭，以非阻塞方式拿出任务，源码中不会阻塞
                        task = this.pool.blockingQueue.poll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(task != null){
                    task.run();
                    System.out.println("线程：" + Thread.currentThread() + "执行完毕");
                }
            }
        }
    }

    //线程池的初始化
    public FixedSizeThreadPool(int poolSize,int taskSize){
        if(poolSize <= 0 || taskSize <= 0){

        }
        this.blockingQueue = new LinkedBlockingQueue<>(taskSize);
        //以线程安全的方式创建一个ArrayList
        this.workers = Collections.synchronizedList(new ArrayList<>());

        for (int i = 0;i < poolSize;i++){
            Worker worker = new Worker(this);
            worker.start();
            workers.add(worker);
        }
    }

    //将任务提交到仓库中
    public boolean submit(Runnable task){
        if(isWorking){
            return this.blockingQueue.offer(task);
        }else{
            return false;
        }
    }

    //关闭的方法
    //关闭的时候需要做的事情：
    //第一步：仓库停止接受任务
    //第二步：一旦仓库中还有任务，就要继续去执行
    //第三步：拿任务的时候，就不该阻塞了
    //第四步：一旦任务已经阻塞了，我们就要去中断它

    private volatile boolean isWorking = true;

    public void shutdown(){
        this.isWorking = false;
        for (Thread thread : workers){
            if(thread.getState().equals(Thread.State.BLOCKED)){
                thread.interrupt();//中断线程
            }
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*ExecutorService executorService = Executors.newFixedThreadPool(3);
        FutureTask<String> task = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "ok";
            }
        });
        executorService.submit(task);
        executorService.shutdown();
        System.out.println(task.get());*/
        FixedSizeThreadPool pool = new FixedSizeThreadPool(3,6);
        for (int i = 0;i < 6;i++){
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("放入一个线程");
                    try {
                        Thread.sleep(2000L);
                    } catch (InterruptedException e) {
                        System.out.println("线程被中断");
                        e.printStackTrace();
                    }
                }
            });
        }
        pool.shutdown();
    }
}
