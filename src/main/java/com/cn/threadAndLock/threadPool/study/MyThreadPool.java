package com.cn.threadAndLock.threadPool.study;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyThreadPool {
    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(2, 1000, TimeUnit.MILLISECONDS, 10);
        for (int i = 0; i < 5; i++) {
            int j = i;
            threadPool.execute(() -> {
                System.out.println("j " + j);
            });
        }
    }
}

class ThreadPool {
    //任务队列
    private BlockingQueue<Runnable> taskQueue;
    //线程集合
    private HashSet<Worker> workers = new HashSet();
    //核心线程数
    private int coreSize;
    //获取任务的超时时间
    private long timeout;
    private TimeUnit timeUnit;

    //执行任务
    public void execute(Runnable task) {
        //当任务数没有超过 coreSize 时，直接交给worker对象执行
        //如果任务数超过了 coreSize 时，加入任务队列暂存
        synchronized (workers) {
            if(workers.size() < coreSize) {
                Worker worker =new Worker(task);
                System.out.println(Thread.currentThread().getName() + " 新增worker " + worker + "，task " + task);
                workers.add(worker);
                worker.start();
            }else {
                System.out.println(Thread.currentThread().getName() + " 加入任务队列 " + task);
                taskQueue.put(task);
            }
        }
    }


    public ThreadPool(int coreSize, long timeout, TimeUnit timeUnit, int queueCapacity) {
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.taskQueue = new BlockingQueue<>(queueCapacity);
    }

    class Worker extends Thread{
        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            //执行任务
            //1）当task不为空，执行任务
            //2）当task执行完毕，再接着从任务队列中获取任务并执行
            while(task != null || (task =taskQueue.take()) != null) {
                try {
                    System.out.println(Thread.currentThread().getName() + " 正在执行任务。。。" + task);
                    task.run();
                }catch (Exception e) {

                }finally {
                    task = null;
                }
            }
            synchronized (workers) {
                System.out.println(Thread.currentThread().getName() + " worker被移除" + this);
                workers.remove(this);
            }
        }
    }
}

class BlockingQueue<T> {
    //1.任务队列
    private Deque<T> queue = new ArrayDeque<>();

    //2.锁
    private ReentrantLock lock = new ReentrantLock();

    //3.生产者条件变量
    private Condition fullWaitSet = lock.newCondition();

    //4.消费者条件变量
    private Condition emptyWaitSet = lock.newCondition();

    //5.容量
    private int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    //带超时的阻塞获取
    public T poll(long timeout, TimeUnit unit) {
        lock.lock();
        try {
            //将timeout统一转换为纳秒
            long nanos = unit.toNanos(timeout);
            while(queue.isEmpty()) {
                try {
                    //返回的是剩余时间
                    if(nanos <= 0) {
                        return null;
                    }
                    nanos = emptyWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = queue.removeFirst();
            fullWaitSet.signal();
            return t;
        }finally {
            lock.unlock();
        }
    }

    //阻塞获取
    public T take() {
        lock.lock();
        try {
            while(queue.isEmpty()) {
                try {
                    emptyWaitSet.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = queue.removeFirst();
            fullWaitSet.signal();
            return t;
        }finally {
            lock.unlock();
        }
    }

    //阻塞添加
    public void put(T element) {
        lock.lock();
        try {
            while(queue.size() == capacity) {
                try {
                    fullWaitSet.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.addLast(element);
            emptyWaitSet.signal();
        }finally {
            lock.unlock();
        }
    }

    //获取大小
    public int size() {
        lock.lock();
        try {
            return queue.size();
        }finally {
            lock.unlock();
        }
    }
}
