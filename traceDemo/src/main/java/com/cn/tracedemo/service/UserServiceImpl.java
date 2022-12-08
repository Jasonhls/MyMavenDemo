package com.cn.tracedemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author HeLiSen
 * @Date 2022/12/8 15:37
 */
@Service
@Slf4j
public class UserServiceImpl implements IUserService{

    /**
     * @Async注解需要在启动类上添加@EnableAsync才能生效
     * 执行了线程池的名称为MyExecutor，前提是必须定义BeanName为MyExecutor的线程池实例
     */
    @Async("MyExecutor")
    @Override
    public void insertUser() throws InterruptedException {
        Thread.sleep(2000);
        log.info("正在执行插入User。");
    }
}
