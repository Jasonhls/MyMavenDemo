package com.cn.aop.proxy;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jason on 2018/11/5.
 */
@RestController
@RequestMapping(value = "/proxy")
public class TestProxyController {

    @GetMapping(value = "/jdk")
    public Map testJdkProxy(){
        Map map = new HashMap();
        Student stu = new Student();
        JDKProxyFactory jpf = new JDKProxyFactory(stu);
        //这里只能转成接口，不能转成具体的实现类
//        Student pst = (Student) jpf.getProxyObject();
        Person pst = (Person) jpf.getProxyObject();
        return pst.eat(map);
    }

    @GetMapping(value = "/cglib")
    public Map testCglibProxy(){
        Map map = new HashMap();
        CglibProxyFactory cpf = new CglibProxyFactory(new Student());
        //这里可以转换为具体的实现类
        Student student = (Student) cpf.getProxyObject();
        return student.eat(map);
    }
}
