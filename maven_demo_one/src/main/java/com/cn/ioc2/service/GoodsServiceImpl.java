package com.cn.ioc2.service;

import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: helisen
 * @create: 2020-07-03 11:32
 **/
@Service
public class GoodsServiceImpl implements GoodsService{
    @Override
    public String sayHello(String name) {
        return "hello " + name;
    }
}
