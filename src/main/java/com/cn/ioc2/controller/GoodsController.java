package com.cn.ioc2.controller;

import com.cn.ioc2.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: helisen
 * @create: 2020-07-03 11:32
 **/
@RestController
@RequestMapping(value = "/goods")
public class GoodsController {

    public GoodsController() {
        System.out.println("调用了GoodsController的无参构造");
    }

    @Autowired
    private GoodsService goodsService;

    @GetMapping(value = "/sayHello")
    public String sayHello(String name) {
        return goodsService.sayHello(name);
    }

}
