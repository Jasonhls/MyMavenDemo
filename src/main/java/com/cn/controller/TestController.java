package com.cn.controller;

import com.cn.threadAndLock.threadPool.test.TestThreadPoolManager;
import com.cn.websocket.MyWebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Queue;
import java.util.UUID;

/**
 * Created by Jason on 2018/10/17.
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {
    @Autowired
    private TestThreadPoolManager testThreadPoolManager;

    @Autowired
    private MyWebSocket webSocket;

    @GetMapping(value = "/addNum")
    public void addNum(Integer num) throws IOException {
        webSocket.sendMessage(num +"");
    }

    /**
     * 测试模拟下单请求
     * @param id
     * @return
     */
    @GetMapping(value = "/start/{id}")
    public String start(@PathVariable Long id){
        String orderNo = System.currentTimeMillis() + UUID.randomUUID().toString();
        testThreadPoolManager.addOrders(orderNo);

        return "Test ThreadPoolExecutor start";
    }

    @GetMapping(value = "/end/{id}")
    public String end(@PathVariable Long id){
        testThreadPoolManager.shutDown();

        Queue q = testThreadPoolManager.getMsgQueue();
        System.out.println("关闭了线程服务，还有未出来的信息条数：" + q.size());

        return "Test ThreadPoolExecutor end";
    }
}
