package com.cn.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jason on 2018/11/5.
 */
@Aspect
@Order(1)
@Component
public class LogAscept {

    @Pointcut(value = "execution(* com.cn.service..*(..))")
    public void servicePointCut(){};

    @Before(value = "servicePointCut()")
    public void before(){
        System.out.println("before" + getCurrentTime(new Date()));
        System.out.println("开始拦截service");
    }

    @After(value = "servicePointCut()")
    public void after(){
        System.out.println("after" + getCurrentTime(new Date()));
        System.out.println("拦截service完成了");
    }

    @Around(value = "servicePointCut()")
    public Object around(ProceedingJoinPoint joinPoint){
        long start = System.currentTimeMillis();
        System.out.println("around start" + getCurrentTime(new Date()));
        try {
            Object proceed = joinPoint.proceed();
            long end = System.currentTimeMillis();
            System.out.println("执行时间：" + (end - start));
            System.out.println("around end" + getCurrentTime(new Date()));
            return proceed;
        } catch (Throwable throwable) {
            long end = System.currentTimeMillis();
            System.out.println("执行时间：" + (end - start));
            throwable.printStackTrace();
        }
        return null;
    }

    private String getCurrentTime(Date date){
        //yyyy-MM-dd HH:mm:ss 返回的为24小时制的，而yyyy-MM-dd hh:mm:ss返回的为12小时制的
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
}
