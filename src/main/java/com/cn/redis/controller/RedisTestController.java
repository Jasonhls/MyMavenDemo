package com.cn.redis.controller;

import com.cn.redis.pojo.StuDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/redisTest")
public class RedisTestController {
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping(value = "/set")
    public void set(String key,String name,int age){
        StuDTO stuDTO = new StuDTO(name,age);
        redisTemplate.opsForValue().set(key,stuDTO);
    }

    @GetMapping(value = "/get")
    public StuDTO get(String key){
        return (StuDTO) redisTemplate.opsForValue().get(key);
    }
}
