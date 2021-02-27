package com.cn.designMode.adapter.testExample;

public interface HandlerAdapter {
    boolean supports(Object handler);
    void handler(Object handler);
}
