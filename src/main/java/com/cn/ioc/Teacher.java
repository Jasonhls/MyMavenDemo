package com.cn.ioc;

import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: helisen
 * @create: 2020-07-02 09:01
 **/
@Component
public class Teacher {
    public Teacher() {
        System.out.println("调用老师的无参构造函数");
    }
}
