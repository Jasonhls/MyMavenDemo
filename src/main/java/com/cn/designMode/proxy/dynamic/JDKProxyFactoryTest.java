package com.cn.designMode.proxy.dynamic;

public class JDKProxyFactoryTest {
    public static void main(String[] args){
        ProxyStudent s = new ProxyStudent();
        JDKProxyFactory jdkProxyFactory = new JDKProxyFactory(s);
        ProxyPerson proxyTarget = (ProxyPerson)jdkProxyFactory.getProxyTarget();
        proxyTarget.eat();
        proxyTarget.walk("3000米");
    }
}
