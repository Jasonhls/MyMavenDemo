package com.cn.threadAndLock.lock.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @description:
 * @author: helisen
 * @create: 2021-04-09 14:16
 **/
//自定义锁（不可重入）
public class MyLock implements Lock {

    //独占锁
    class MySync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int arg) {
            if(compareAndSetState(0, 1)) {
                //加上了锁，并设置owner为当前线程
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            setExclusiveOwnerThread(null);
            setState(0);//带有volatile修饰符，应该把setExclusiveOwnerThread放在前面
            return true;
        }

        @Override//是否持有独占锁
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        public Condition newCondition() {
            return new ConditionObject();
        }
    }


    private MySync sync = new MySync();


    @Override//加锁（不成功会进入等待队列）
    public void lock() {
        sync.acquire(1);
    }

    @Override//加锁，可打断
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override//尝试加锁（一次）
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override//尝试加锁，带超时
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override//解锁
    public void unlock() {
        //release会唤醒正在阻塞的线程，而tryRelease不会，仅仅只是把state置为0，owner线程置为null。
        sync.release(1);
    }

    @Override//创建条件变量
    public Condition newCondition() {
        return sync.newCondition();
    }
}
