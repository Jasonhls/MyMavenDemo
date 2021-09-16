package com.cn.designMode.adapter.testExample;

import com.cn.designMode.adapter.testExample.controller.SimpleController;

public class SimpleHandleAdapter implements HandlerAdapter{
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof SimpleController);
    }

    @Override
    public void handler(Object handler) {
        ((SimpleController)handler).doSimpleHandler();
    }
}
