package com.cn.aop.proxy;

import java.util.Map;

/**
 * Created by Jason on 2018/11/5.
 */
public class Student implements Person {
    @Override
    public Map eat(Map map) {
        map.put("student","学生吃饭");
        return map;
    }
}
