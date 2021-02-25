package com.cn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Jason on 2018/10/18.
 */
@RestController
@RequestMapping(value = "/queue")
public class QueueController {
    /*一般controller中不会定义成员变量，这里为了测试同一个Queue，而Controller类本来默认就是单例的，所以就定义了一个成员变量*/
    private Queue queue = new LinkedBlockingQueue(3);//这里3限制最多只能有三个元素，如果没有指定，就按默认值

    @RequestMapping("/add/{element}")
    public Queue addElements(@PathVariable String element){
        boolean add = queue.add(element);//向队列中添加一个元素
        return queue;
    }

    @GetMapping("/poll")
    public Queue pollElements(){
        Object poll = queue.poll();//从队列中删除第一个元素，返回值为删除的元素
        return queue;
    }

    @GetMapping(value = "/peek")
    public Queue peekElements(){
        Object peek = queue.peek();//在队列头部查询元素，返回值为查询到的元素
        return queue;
    }
}
