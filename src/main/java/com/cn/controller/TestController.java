package com.cn.controller;

import com.cn.websocket.MyWebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @description:
 * @author: helisen
 * @create: 2020-11-23 09:19
 **/
@RestController
@RequestMapping(value = "/test")
public class TestController {
    @Autowired
    private MyWebSocket webSocket;

    @GetMapping(value = "/addNum")
    public void addNum(Integer num) throws IOException {
        webSocket.sendMessage(num +"");
    }
}
