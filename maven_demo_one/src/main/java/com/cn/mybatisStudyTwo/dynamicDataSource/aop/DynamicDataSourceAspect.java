package com.cn.mybatisStudyTwo.dynamicDataSource.aop;

import cn.hutool.core.collection.CollectionUtil;
import com.cn.mybatisStudyTwo.dynamicDataSource.DynamicDataSourceHolder;
import com.cn.mybatisStudyTwo.dynamicDataSource.annotation.DataSource;
import com.cn.mybatisStudyTwo.dynamicDataSource.config.SystemProperties;
import com.cn.mybatisStudyTwo.dynamicDataSource.properties.SlaveDruidDataSourceProperties;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;
import java.util.List;

@Slf4j
@Aspect
public class DynamicDataSourceAspect {

    private List<String> readMethod = Lists.newArrayList("select", "get", "find", "query", "count", "list", "pageList", "customList");

    @Autowired
    private SystemProperties systemProperties;
    @Autowired
    private SlaveDruidDataSourceProperties sourceProperties;


    /**
     * 会拦截带有注解@DataSource的类或方法和所有的ServiceImpl类
     */
    @Pointcut(value = "@annotation(com.cn.mybatisStudyTwo.dynamicDataSource.annotation.DataSource) " +
            "|| execution(* *..*.*ServiceImpl.*(..))")
    public void cut(){}


    @Around(value = "cut()")
    public Object changeDataSourceByAop(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        if(method.isAnnotationPresent(DataSource.class)) {
            String dataSourceName = method.getAnnotation(DataSource.class).value();
            if(!DynamicDataSourceHolder.slaveDataSourceNames.contains(dataSourceName)) {
                log.error("数据源" + dataSourceName + "不存在于配置的数据源集合中");
            }else {
                log.info("使用数据源：{}", dataSourceName);
                DynamicDataSourceHolder.clearDataSource();
                //设置通过注解@DataSource指定的数据源
                DynamicDataSourceHolder.setDataSource(dataSourceName);
            }
        }else {
            if(systemProperties.getReadWriteSpilt()) {
                boolean shouldDo = false;
                String methodName = method.getName();
                for (String name : readMethod) {
                    if(methodName.startsWith(name)) {
                        shouldDo = true;
                        break;
                    }
                }
                if(CollectionUtil.isNotEmpty(systemProperties.getReadMethod())) {
                    for (String name : systemProperties.getReadMethod()) {
                        if(methodName.startsWith(name)) {
                            shouldDo = true;
                            break;
                        }
                    }
                }
                if(shouldDo) {
                    DynamicDataSourceHolder.clearDataSource();
                    //取从数据库集合中的第一个数据库
                    DynamicDataSourceHolder.setDataSource(sourceProperties.getSalvesProperties().get(0).getDataSourceName());
                }
            }
        }

        return joinPoint.proceed(joinPoint.getArgs());
    }
}
