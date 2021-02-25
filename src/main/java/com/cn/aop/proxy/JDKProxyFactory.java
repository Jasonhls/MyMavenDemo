package com.cn.aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Jason on 2018/11/5.
 */
public class JDKProxyFactory implements InvocationHandler{
    private Object target;

    public JDKProxyFactory(Object target){
        this.target = target;
    }

    public Object getProxyObject(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object obj = method.invoke(target,args);
        if(obj == target){
            System.out.println("同一个对象");
        }
        System.out.println("增强的方法：" + method.getName());
        return obj;
    }
}
