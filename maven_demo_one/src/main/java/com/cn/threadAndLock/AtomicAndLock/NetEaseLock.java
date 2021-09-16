package com.cn.threadAndLock.AtomicAndLock;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by Jason on 2019/3/2.
 * 模拟ReentrantLock的源码生成逻辑
 */
public class NetEaseLock implements Lock {
    //标志：谁拿到了锁
    AtomicReference<Thread> owner = new AtomicReference<>();

    /*集合 存储正在等待的线程*/
    public LinkedBlockingDeque<Thread> waiters = new LinkedBlockingDeque<>();

    @Override
    public void lock() {
        //把Thread.currentThread()给owner，如果赋值失败(返回false)，
        // 表示owner有值，不为空，即表示owner拥有锁，那么当前线程必须等待，现在无法获取到锁
        //compareAndSet内存赋值方法，直接操作内存
        //compareAndSet(V expect,V update)方法中的expect指的是该对象期望中的现在的值(有可能不等于实际内存中现在的值)，
        //update指的是需要更新后的值，如果期望的当前值不等于内存的实际值，那么compareAndSet会返回false
        while(!owner.compareAndSet(null,Thread.currentThread())){
            waiters.add(Thread.currentThread());//如果没有更新成功，那么把当前线程加入等待队列中
            LockSupport.park();//让当前线程等待,如果等待的线程被唤醒，接着从原来等待处往下面执行
            /*当前线程被唤醒，需要将当前线程从等待线程集合中移除*/
            waiters.remove(Thread.currentThread());
        }
    }

    @Override
    public void unlock() {
        if(owner.compareAndSet(Thread.currentThread(),null)){//如果当前线程可以更新为空，表示当前线程释放了锁
            //通知其他线程，可以继续抢夺锁了
            Object[] objects = waiters.toArray();
            /*遍历唤醒其他的锁*/
            for (Object object : objects){
                Thread t = (Thread) object;
                LockSupport.unpark(t);
            }
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
