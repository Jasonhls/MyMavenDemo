package com.cn.controller;

import com.cn.async.AsyncSetter;
import com.cn.pojo.Result;
import com.cn.service.TestAsyncSetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jason on 2018/10/18.
 */
@RestController
@RequestMapping(value = "/asyncSetter")
public class TestAsyncSetterController {

    @Autowired
    private AsyncSetter<Result> asyncSetter;
    @Autowired
    private TestAsyncSetterService testAsyncSetterService;

    @GetMapping(value = "")
    public List<Result> testAsyncSetter(){
        List<Result> list = new ArrayList<>();
        Result r = new Result();
        asyncSetter.setOriginalObj(r).addRunnable(result -> {
            System.out.println(r == result);
            testAsyncSetterService.testTask(result,"任务名称");
        }).execute();
        list.add(asyncSetter.getOriginalObj());
        return list;
    }
}
