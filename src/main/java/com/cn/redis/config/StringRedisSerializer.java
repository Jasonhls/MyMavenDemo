package com.cn.redis.config;


import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.Assert;

import java.nio.charset.Charset;

/**
 * 兼容key为object的情况，需要设置key的序列化方式为下面自定义的StringRedisSerializer
 */
public class StringRedisSerializer implements RedisSerializer<Object> {
    private final Charset charset;
    private final String target = "\"";
    private final String replacement = "";

    public StringRedisSerializer() {
        this(Charset.forName("UTF8"));    }
        public StringRedisSerializer(Charset charset) {
        Assert.notNull(charset, "Charset must not be null!");
        this.charset = charset;
    }
    @Override
    public String deserialize(byte[] bytes) {
        return (bytes == null ? null : new String(bytes, charset));
    }

    @Override
    public byte[] serialize(Object object) {
        String string = JSON.toJSONString(object);
        if (string == null) {            return null;        }
        string = string.replace(target, replacement);
        return string.getBytes(charset);
    }
}
