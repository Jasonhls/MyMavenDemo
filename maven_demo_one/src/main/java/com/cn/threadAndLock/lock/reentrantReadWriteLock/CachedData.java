package com.cn.threadAndLock.lock.reentrantReadWriteLock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CachedData {
    Object data;
    //是否有效，如果失效，需要重新计算data
    volatile boolean cacheValid;
    final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    void processCachedData() {
        rwl.readLock().lock();
        if(!cacheValid) {
            //获取写锁之前必须释放读锁
            rwl.readLock().unlock();
            rwl.writeLock().lock();
            try {
                //判断是否有其他线程已经获取了写锁、更新了缓存，避免重复更新
                if(!cacheValid) {
                    data = new Object();
                    cacheValid = true;
                }
                //降级为读锁，释放写锁，这样能够让其他线程读取缓存
                rwl.readLock().lock();
            }finally {
                rwl.writeLock().unlock();
            }
        }
        //自己用完数据，释放读锁
        try {
            use(data);
        }finally {
            rwl.readLock().unlock();
        }
    }

    private void use(Object data) {
        //...
    }
}
