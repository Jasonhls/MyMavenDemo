package com.cn.thread.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: helisen
 * @create: 2020-05-28 16:52
 **/
public class BoundedQueue<T> {
    private Object[] items;
    private int addIndex, removeIndex, count;
    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    public BoundedQueue(int size) {
        items = new Object[size];
    }

    public void add(T t) throws InterruptedException {
        lock.lock();
        try {
            while(count == items.length){
                //如果count等于数组长度，表示满元素了
                notFull.await();
            }
            items[addIndex] = t;
            if(++addIndex == items.length) {
                addIndex = 0;
            }
            ++count;
            //添加元素，现在肯定不是空的
            notEmpty.signal();
        }finally {
            lock.unlock();
        }
    }

    public T remove() throws InterruptedException {
        lock.lock();
        try {
            while(count == 0) {
                //没有元素，表示为空
                notEmpty.await();
            }
            Object t = items[removeIndex];
            if(++removeIndex == items.length) {
                removeIndex = 0;
            }
            --count;
            //移除元素，现在肯定没满
            notFull.signal();
            return(T) t;
        }finally {
            lock.unlock();
        }
    }
}
