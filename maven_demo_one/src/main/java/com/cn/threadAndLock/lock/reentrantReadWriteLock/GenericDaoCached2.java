package com.cn.threadAndLock.lock.reentrantReadWriteLock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class GenericDaoCached2 extends GenericDao{

    private GenericDao dao = new GenericDao();
    private Map<SqlPair, Object> map = new HashMap<>();
    private ReentrantReadWriteLock rw = new ReentrantReadWriteLock();


    @Override
    public List queryList(Class beanClass, String sql, Object... args) {
        return super.queryList(beanClass, sql, args);
    }

    @Override
    public Object queryOne(Class beanClass, String sql, Object... args) {
        SqlPair key = new SqlPair(sql, args);
        //读的部分加读锁
        rw.readLock().lock();
        try {
            //先从缓存中找，找到直接返回
            Object value = map.get(key);
            if(value != null) {
                return value;
            }
        }finally {
            rw.readLock().unlock();
        }

        //写的部分加写锁
        rw.writeLock().lock();
        try {
            Object value = map.get(key);
            //这里二次检查，是因为多线程情况下，第一个线程进来成功了，会去查询数据库，并把数据库放入了缓存，
            //后面的线程再进来，都直接从缓存中获取数据，不用再查询数据库了。
            if(value == null) {
                //缓存没有命中，查询数据库
                value = dao.queryOne(beanClass, sql, args);
                map.put(key, value);
            }
            return value;
        }finally {
            rw.writeLock().unlock();
        }
    }

    /**
     *这里加了锁之后，先查询数据库还是先更新缓存，没有区别了
     */
    @Override
    public int update(String sql, Object... args) {
        //加写锁
        rw.writeLock().lock();
        try {
            //先查询数据库，再清空缓存
            int update = dao.update(sql, args);
            //清理缓存
            map.clear();
            return update;
        }finally {
            rw.writeLock().unlock();
        }
    }

    class SqlPair {
        private String sql;
        private Object[] args;

        public SqlPair(String sql, Object[] args) {
            this.sql = sql;
            this.args = args;
        }
    }
}
