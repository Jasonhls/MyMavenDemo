package com.cn.designMode.adapter.testExample;

import com.cn.designMode.adapter.testExample.controller.HttpController;

public class HttpHandlerAdapter implements HandlerAdapter{
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof HttpController);
    }

    @Override
    public void handler(Object handler) {
        ((HttpController)handler).doHttpHandler();
    }
}
