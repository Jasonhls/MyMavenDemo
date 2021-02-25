package com.cn.designMode.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxyFactory implements InvocationHandler {
    private Object target;

    public JDKProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxyTarget(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object obj = method.invoke(target, args);
        System.out.println("经过了动态代理了");
        System.out.println("方法返回值：" + obj);
        return obj;
    }
}
