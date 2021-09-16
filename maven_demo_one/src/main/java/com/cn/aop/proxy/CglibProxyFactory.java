package com.cn.aop.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by Jason on 2018/11/5.
 */
public class CglibProxyFactory implements MethodInterceptor {
    private Object target;

    public CglibProxyFactory(Object target){
        this.target = target;
    }

    public Object getProxyObject(){
        //1.实例化cglib代理增强器
        Enhancer enhancer = new Enhancer();
        //2.在增强器上设置相应的属性值
        //设置目标的类:通过目标类对象来生成代理子对象
        enhancer.setSuperclass(target.getClass());
        //设置回调方法函数,参数：回调的对象（写增强方法的代码）
        enhancer.setCallback(this);
        //通过增强器获得代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib增强方法");
        Object obj = methodProxy.invoke(o,objects);
        return obj;
    }
}
