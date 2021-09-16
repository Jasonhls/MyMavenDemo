package com.cn.cache.annotationCache;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * aop适用于增强功能
 */
@Component
@Slf4j
@Aspect
public class CacheAspect {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 缓存雪崩，高并发缓存出错，方案二：记录缓存出现异常的一瞬间，每一个查询的缓存重建标志
     */
    ConcurrentHashMap<String,String> cacheBuildFlag = new ConcurrentHashMap<>();

    /**
     * @param joinPoint 切点
     * @return
     * @throws Throwable
     */
    @Around("@annotation(com.cn.cache.annotationCache.NeteaseEduCache)")
    public Object doAnything(ProceedingJoinPoint joinPoint) throws Throwable {
        String key = null;
        //1.从方法的注解的值中获取key
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();//获取切点的签名
        Class<?> aClass = joinPoint.getTarget().getClass();//获取该切点的类class
        Method method = aClass.getMethod(methodSignature.getName(), methodSignature.getMethod().getParameterTypes());
        NeteaseEduCache annotation = method.getAnnotation(NeteaseEduCache.class);
        String keyEL = annotation.key();//keyEL值示例： 'netease_' + #id
        //通过springEL表达式解析keyEL

        //创建解析器
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression(keyEL);
        //设置上下文（有哪些占有符，以及每种占位符的值）
        EvaluationContext context = new StandardEvaluationContext();

        DefaultParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();
        String[] parameterNames = discoverer.getParameterNames(method);//获取切点处方法的所有参数名
        Object[] args = joinPoint.getArgs();//获取切点处方法的所有参数值
        for (int i = 0;i < parameterNames.length;i++){
            context.setVariable(parameterNames[i],args[i]);
        }

        //解析
        key = expression.getValue(context).toString();

        //2.从redis查询数据
        Object value = redisTemplate.opsForValue().get(key);
        //3.如果有，直接返回
        if(value != null){
            log.info("从redis缓存中获取到了数据");
            return value;
        }

        /**
         * 缓存失效 -- 容错'异常情况' 仅仅针对高并发，比如redis突然挂了
         * 将2000并发转换成同步操作
         /

         /* 方案一：添加锁，让2000个并发一个一个来执行
         * 优点：简单有效，使用范围广
         * 缺点：锁阻塞其他线程，所得颗粒度太粗
         */
        /*synchronized (CacheAspect.classAndInterface){ //1个请求拿到锁，重建缓存，其他1999个请求等待重建完毕
            //2.从redis查询数据
            Object value = redisTemplate.opsForValue().get(key);
            //3.如果有，直接返回
            if(value != null){
                log.info("从redis缓存中获取到了数据");
                return value;
            }

            //4.如果没有，就执行原来的方法
            value = joinPoint.proceed();
            //5.将数据进行缓存
            redisTemplate.opsForValue().set(key,value);
        }*/

        /**
         * 方案二:记录缓存出现异常的一瞬间，每一个查询的缓存重建标志
         */
        boolean flag = false;
        try {
            flag = cacheBuildFlag.putIfAbsent(key, "true") == null;
            if(flag){//2000个线程并发，一个成功，就去进行缓存重建
                //4.如果没有，就执行原来的方法
                value = joinPoint.proceed();
                //5.将数据进行缓存
                redisTemplate.opsForValue().set(key,value);
            }else {//剩余1999个没有设置成功
                //1.隔一段时间，系统自动重试... sleep(111),再次查询
                //结合业务场景分析，实际情况去分析
                //2.返回错误码
                value = new Object();//系统处理崩溃边缘，返回空
                //3.降级

            }
        } finally {
            if(flag){
                cacheBuildFlag.remove(key);
            }
        }

        //4.如果没有，就执行原来的方法
        value = joinPoint.proceed();
        //5.将数据进行缓存
        try{
            redisTemplate.opsForValue().set(key,value);
        }catch (Exception e){
            log.error("e:{}",e);
        }
        return value;
    }

}
