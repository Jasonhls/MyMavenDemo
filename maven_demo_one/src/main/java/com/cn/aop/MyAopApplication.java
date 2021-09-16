package com.cn.aop;

import com.cn.aop.service.AopTestService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@ComponentScan("com.example.demo.aop.*")
public class MyAopApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyAopApplication.class);
        AopTestService bean = context.getBean(AopTestService.class);
        System.out.println(bean.sayHello("hls"));
    }
}
