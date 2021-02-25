package com.cn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jason on 2018/10/17.
 * controller ,service，dao默认都是单例的，即@Scope(value = "single")，单例的类，如果有成员变量，只要被调用，其值一直变化
 * 如果是设置为多例的，即@Scope(value = "prototype")，那么每次就新建一个类，该成员变量的初始值不会变
 * 单例是线程不安全的
 */
@RestController
@RequestMapping(value = "/single")
//@Scope(value = "prototype")
public class SingleTonController {
    private int i = 0;

    @GetMapping(value = "/a")
    public String addA(){
        System.out.println("a接口：i现在为：" + i);
        i++;
        System.out.println("a接口增加后i为：" + i);
        return i + "";
    }

    @GetMapping(value = "/b")
    public String addB(){
        System.out.println("b接口：i现在为：" + i);
        i++;
        System.out.println("b接口增加后i为：" + i);
        return i + "";
    }
}
