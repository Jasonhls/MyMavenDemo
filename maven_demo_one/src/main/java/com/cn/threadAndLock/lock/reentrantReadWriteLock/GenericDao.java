package com.cn.threadAndLock.lock.reentrantReadWriteLock;

import java.util.ArrayList;
import java.util.List;

public class GenericDao<T> {
    public <T> List<T> queryList(Class<T> beanClass, String sql, Object... args) {
        return new ArrayList<>();
    }

    public <T> T queryOne(Class<T> beanClass, String sql, Object... args) {
        return null;
    }

    public int update(String sql, Object... args) {
        //...
        return 1;
    }
}
