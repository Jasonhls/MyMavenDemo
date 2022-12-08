package com.cn.tracedemo.controller;

import com.cn.tracedemo.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author HeLiSen
 * @Date 2022/12/8 15:15
 */
@RestController
@RequestMapping(value = "/test")
@Slf4j
public class TestController {
    @Autowired
    private IUserService iUserService;


    @PostMapping("doTest")
    public String doTest(@RequestParam("name")String name) throws InterruptedException {
        log.info("入参 name={}", name);
        testTrace();
        log.info("调用结束 name={}", name);
        iUserService.insertUser();
        return "Hello," + name;
    }

    private void testTrace() {
        log.info("这是一行info日志");
        log.info("这是一行error日志");
        testTrace2();
    }

    private void testTrace2() {
        log.info("这也是一行info日志");
    }
}
