package com.cn.service.impl;

import com.cn.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * Created by Jason on 2018/11/5.
 */
@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "say Hello to " + name;
    }
}
