package com.cn.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AsyncDemoServiceImpl implements AsyncDemoService{
    @Override
    public void addPerson(String name) {
      log.info("add person:{}",name);
      sendMessage("add person" + name);//异步执行的
      log.info("add finish");
    }

    @Async(value = "taskExecutor")//异步默认使用的线程池不行，就用自定义的
    @Override
    public void sendMessage(String message) {
        log.info("send message success");
    }
}
