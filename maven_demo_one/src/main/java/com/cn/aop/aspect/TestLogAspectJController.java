package com.cn.aop.aspect;

import com.cn.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jason on 2018/11/5.
 */
@RestController
@RequestMapping(value = "/aspectj")
public class TestLogAspectJController {
    @Autowired
    private HelloService helloService;

    @GetMapping(value = "/sayHello")
    public String sayHello(String name){
        return helloService.sayHello(name);
    }
}
