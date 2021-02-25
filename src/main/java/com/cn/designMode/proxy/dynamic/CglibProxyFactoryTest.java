package com.cn.designMode.proxy.dynamic;

public class CglibProxyFactoryTest {
    public static void main(String[] args){
        ProxyStudent s = new ProxyStudent();
        CglibProxyFactory proxyFactory = new CglibProxyFactory(s);
        ProxyPerson p = (ProxyPerson)proxyFactory.getProxyTarget();
        p.eat();
        p.walk("3000米");
    }
}
