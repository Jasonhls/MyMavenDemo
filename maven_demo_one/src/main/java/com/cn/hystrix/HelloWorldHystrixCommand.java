package com.cn.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * @description:
 * @author: helisen
 * @create: 2021-05-20 15:35
 **/
public class HelloWorldHystrixCommand extends HystrixCommand<String> {

    private final String name;
    public HelloWorldHystrixCommand(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }


    @Override
    protected String run() throws Exception {
//        当前线程：hystrix-ExampleGroup-1
        System.out.println("当前线程：" + Thread.currentThread().getName());
        return "hello, " + name;
    }
}
