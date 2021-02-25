package com.cn.cache.annotationCache;

import java.lang.annotation.*;

/**
 * 缓存注解，类似与spring+redis实现的@cacheable注解
 */
@Target(ElementType.METHOD)//用于方法上
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NeteaseEduCache {
    String key();
}
