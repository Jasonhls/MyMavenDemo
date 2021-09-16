package com.cn.designMode.adapter.testExample;

import com.cn.designMode.adapter.testExample.controller.Controller;
import com.cn.designMode.adapter.testExample.controller.HttpController;

import java.util.ArrayList;
import java.util.List;

public class DispatcherServlet {
    private List<HandlerAdapter> handlerAdapters = new ArrayList<>();

    public DispatcherServlet() {
        handlerAdapters.add(new AnnotationHandlerAdapter());
        handlerAdapters.add(new SimpleHandleAdapter());
        handlerAdapters.add(new HttpHandlerAdapter());
    }

    public HandlerAdapter getHandler(Controller controller) {
        for (HandlerAdapter adapter : handlerAdapters) {
            if(adapter.supports(controller)) {
                return adapter;
            }
        }
        return null;
    }

    public void doDispatch() {
        //此处模拟SpringMVC从request去handler对象
        //适配器可以获取希望的Controller
//        AnnotationController controller = new AnnotationController();
//        SimpleController controller = new SimpleController();
        HttpController controller = new HttpController();
        //得到对应的适配器
        HandlerAdapter handler = getHandler(controller);
        //通过适配器执行对应的controller对应方法
        handler.handler(controller);
    }

    //测试
    public static void main(String[] args) {
        new DispatcherServlet().doDispatch();
    }
}
