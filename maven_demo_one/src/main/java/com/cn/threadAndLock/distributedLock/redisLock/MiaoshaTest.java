package com.cn.threadAndLock.distributedLock.redisLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/miaoshaTest")
public class MiaoshaTest {
    @Autowired
    private RedisTemplate redisTemplate;
    @GetMapping(value = "/test1")
    public boolean testOne(){
        boolean b = (boolean)redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                //该方法，第一个参数为key，第二个参数为value
                Boolean result = redisConnection.set("".getBytes(), "~".getBytes(), Expiration.seconds(10),
                        RedisStringCommands.SetOption.SET_IF_ABSENT);
                byte[] bytes = redisConnection.get("".getBytes());
                for (byte by : bytes){
                    System.out.println("返回的字节："+ by);
                }
                return result;
            }
        });
        return b;
    }
}
