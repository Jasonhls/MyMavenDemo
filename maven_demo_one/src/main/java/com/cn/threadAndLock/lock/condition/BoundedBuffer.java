package com.cn.threadAndLock.lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Jason on 2019/2/28.
 * 有界缓存
 */
public class BoundedBuffer {
    final Lock lock = new ReentrantLock();
    final Condition writer = lock.newCondition();
    final Condition reader = lock.newCondition();

    final Object[] items = new Object[100];//缓存队列
    int putptr/*写索引*/,takeptr/*读索引*/,count/*队列中存在的数据个数*/;

    public void put(Object x) throws InterruptedException {
        lock.lock();
        try {
            while(count == items.length)
                writer.await();//如果队列中的数达到了缓存队列的最大长度，写操作就等待
            //如果没有达到缓存队列的最大长度
            items[putptr] = x;
            putptr++;
            if(putptr == items.length){
                putptr = 0;//如果写索引达到缓存队列的最大值，则重置为0
            }
            ++count;//缓存队列中元素个数加一
            reader.signal();//读唤醒
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            if(count == 0)reader.await();//如果缓存队列中没有元素，则读操作等待
            //否则取出元素
            Object x = items[takeptr];
            takeptr++;
            if(takeptr == items.length)takeptr = 0;
            --count;
            writer.signal();//写唤醒
            return x;
        } finally {
            lock.unlock();
        }
    }
}
