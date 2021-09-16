package com.cn.ioc;

import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: helisen
 * @create: 2020-06-30 12:26
 **/
//@Component(value = "Ss")
@Component
public class Student {
    public Student() {
        System.out.println("调用student的构造函数");
    }
}
