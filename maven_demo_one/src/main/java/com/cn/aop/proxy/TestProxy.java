package com.cn.aop.proxy;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jason on 2018/11/5.
 */
public class TestProxy {

    @Test
    public void testJdkProxy() {
        Map map = new HashMap();
        Student stu = new Student();
        JDKProxyFactory jpf = new JDKProxyFactory(stu);
        //这里只能转成接口，不能转成具体的实现类
//        Student pst = (Student) jpf.getProxyObject();
        Person pst = (Person) jpf.getProxyObject();
        System.out.println(pst.eat(map));
    }

    @Test
    public void testCglibProxy(){
        Map map = new HashMap();
        CglibProxyFactory cpf = new CglibProxyFactory(new Student());
        //这里可以转换为具体的实现类
        Student student = (Student) cpf.getProxyObject();
        System.out.println(student.eat(map));
    }
}
