package com.cn.designMode.adapter.testExample;

import com.cn.designMode.adapter.testExample.controller.AnnotationController;

public class AnnotationHandlerAdapter implements HandlerAdapter{
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof AnnotationController);
    }

    @Override
    public void handler(Object handler) {
        ((AnnotationController)handler).doAnnotationHandler();
    }
}
