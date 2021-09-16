package com.cn.cache.annotationCache;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
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

@Component
@Aspect
public class MyCacheAspect {

    //定义切点
    @Pointcut(value = "@annotation(com.cn.cache.annotationCache.MyCache)")
    public void myCacheAspectCut(){

    }


    @Autowired
    private RedisTemplate redisTemplate;

    @Around(value = "myCacheAspectCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Class declaringType = methodSignature.getDeclaringType();
        Class<?> aClass = joinPoint.getTarget().getClass();
        Method method = declaringType.getMethod(methodSignature.getName(), methodSignature.getMethod().getParameterTypes());
        MyCache annotation = method.getAnnotation(MyCache.class);
        String value = annotation.value();

        ExpressionParser expressionParser = new SpelExpressionParser();
        Expression expression = expressionParser.parseExpression(value);
        //根据Method对象获取该方法上所有的入参
        DefaultParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();
        String[] parameterNames = discoverer.getParameterNames(method);

        EvaluationContext context = new StandardEvaluationContext();
        Object[] args = joinPoint.getArgs();
        for (int i = 0;i < parameterNames.length;i++){
            context.setVariable(parameterNames[i],args[i]);
        }
        String key = expression.getValue(context).toString();

        Object obj = redisTemplate.opsForValue().get(key);
        if(obj != null){
            return obj;
        }else {
            Object proceed = joinPoint.proceed();
            redisTemplate.opsForValue().set(key,proceed);
            return proceed;
        }
    }
}
