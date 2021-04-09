package com.cn.threadAndLock.threadPool.study;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyThreadPool {
    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(1, 1000, TimeUnit.MILLISECONDS, 1,
                (queue, task) -> {
                    //1）死等
//                    queue.put(task);
                    //2）带超时等待
                    //在timeout时间内，等待加入任务队列，如果时间到了，还不能加入就放弃加入任务队列中了
//                    queue.offer(task, 1500, TimeUnit.MILLISECONDS);
                    //3) 让调用者放弃任务的执行
//                    System.out.println(Thread.currentThread().getName() + " 放弃任务执行了, " + task);
                    //4) 让调用者抛出异常
                    //抛出异常，主线程中后续的任务就不会执行了
//                    throw new RuntimeException(Thread.currentThread().getName() + " 抛出异常了, " + task);
                    //5) 让调用者自己执行任务
                    task.run();
                }
        );
        for (int i = 0; i < 3; i++) {
            int j = i;
            threadPool.execute(() -> {
                try {
                    Thread.sleep(1000);//设置每个任务执行时间为1秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " j " + j);
            });
        }
    }
}

//定义拒绝策略的接口
interface RejectPolicy<T> {
    void reject(BlockingQueue<T> queue, T task);
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
    //拒绝策略
    private RejectPolicy<Runnable> rejectPolicy;

    //执行任务
    public void execute(Runnable task) {
        //当任务数没有超过 coreSize 时，直接交给worker对象执行
        //如果任务数超过了 coreSize 时，加入任务队列暂存
        synchronized (workers) {
            if(workers.size() < coreSize) {
                Worker worker =new Worker(task);
                System.out.println(Thread.currentThread().getName() + " 新增worker " + worker + "，新增task   " + task);
                workers.add(worker);
                worker.start();
            }else {
                //尝试添加到任务队列中
                taskQueue.tryPut(rejectPolicy, task);
            }
        }
    }


    public ThreadPool(int coreSize, long timeout, TimeUnit timeUnit, int queueCapacity, RejectPolicy<Runnable> rejectPolicy) {
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.taskQueue = new BlockingQueue<>(queueCapacity);
        this.rejectPolicy = rejectPolicy;
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
//            while(task != null || (task = taskQueue.take()) != null) {
                //内部类可以使用外部类的成员变量，使用超时等待之后，到了时间如果还没有取出，就会跳出循环
            while(task != null || (task = taskQueue.poll(timeout, timeUnit)) != null) {
                try {
                    System.out.println(Thread.currentThread().getName() + " 正在执行任务。。。" + task);
                    task.run();
                }catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    task = null;
                }
            }
            //队列中没有任务了，从线程集合中移除该线程
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
                    emptyWaitSet.await();
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

    //带超时的阻塞添加
    public boolean offer(T task, long timeout, TimeUnit unit) {
        lock.lock();
        try {
            long nanos = unit.toNanos(timeout);
            while(queue.size() == capacity) {
                try {
                    if(nanos < 0) {
                        return false;
                    }
                    System.out.println(Thread.currentThread().getName() + " 等待加入任务队列" + task);
                    nanos = fullWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + " 加入任务队列中 " + task);
            queue.addLast(task);
            emptyWaitSet.signal();
            return true;
        }finally {
            lock.unlock();
        }
    }

    //阻塞添加
    public void put(T task) {
        lock.lock();
        try {
            while(queue.size() == capacity) {
                try {
                    System.out.println(Thread.currentThread().getName() + " 等待加入任务队列 " + task);
                    fullWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + " 加入任务队列中 " + task);
            queue.addLast(task);
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

    //执行拒绝策略
    public void tryPut(RejectPolicy<T> rejectPolicy, T task) {
        lock.lock();
        try {
            if(queue.size() == capacity) {
                //具体的拒绝策略交给调用者自己定义，不由线程池定义，可以有以下几种：
                //1) 死等
                //2) 带超时等待
                //3) 让调用者放弃任务的执行
                //4) 让调用者抛出异常
                //5) 让调用者自己执行任务
                rejectPolicy.reject(this, task);
            }else {
                System.out.println(Thread.currentThread().getName() + " 加入任务队列中 " + task);
                queue.addLast(task);
                emptyWaitSet.signal();
            }
        }finally {
            lock.unlock();
        }
    }
}
