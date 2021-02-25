package com.cn.aop.service;

import org.springframework.stereotype.Service;

@Service
public class AopTestServiceImpl implements AopTestService {
    @Override
    public String sayHello(String name) {
        return "hello " + name;
    }
}
