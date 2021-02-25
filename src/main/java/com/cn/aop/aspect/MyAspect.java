package com.cn.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {
    @Pointcut("execution(* com.example.demo.aop.service.*.*(..))")
    public void pointCut(){}

    @Before("pointCut()")
    public void before(){
        System.out.println("aop前置拦截");
    }

    @After("pointCut()")
    public void after(){
        System.out.println("aop后置拦截");
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint p){
        System.out.println("before1");
        Object o = null;
        try {
            o = p.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("after1");
        return o;
    }
}
