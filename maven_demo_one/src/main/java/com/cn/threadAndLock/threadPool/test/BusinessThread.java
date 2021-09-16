package com.cn.threadAndLock.threadPool.test;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Jason on 2018/10/17.
 */
@Component
@Scope(value = "prototype")//spring多例
public class BusinessThread implements Runnable{
    private String acceptStr;

    public String getAcceptStr() {
        return acceptStr;
    }

    public void setAcceptStr(String acceptStr) {
        this.acceptStr = acceptStr;
    }

    public BusinessThread(String acceptStr) {
        this.acceptStr = acceptStr;
    }

    @Override
    public void run() {
        System.out.println("多线程已经处理订单插入系统，订单号：" + acceptStr);
    }
}
