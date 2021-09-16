package com.cn.threadAndLock.lock.reentrantReadWriteLock;

import java.util.*;

public class GenericDaoCached extends GenericDao{

    private GenericDao dao = new GenericDao();
    private Map<SqlPair, Object> map = new HashMap<>();


    @Override
    public List queryList(Class beanClass, String sql, Object... args) {
        return super.queryList(beanClass, sql, args);
    }

    @Override
    public Object queryOne(Class beanClass, String sql, Object... args) {
        //先从缓存中找，找到直接返回
        SqlPair key = new SqlPair(sql, args);
        Object value = map.get(key);
        if(value != null) {
            return value;
        }
        //缓存没有命中，查询数据库
        value = dao.queryOne(beanClass, sql, args);
        map.put(key, value);
        return value;
    }

    @Override
    public int update(String sql, Object... args) {
        //清理缓存
        map.clear();
        return dao.update(sql, args);
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
