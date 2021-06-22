package com.cn.mybatisStudy.interceptor.logicDelete;

import com.baomidou.mybatisplus.core.parser.AbstractJsqlParser;
import com.baomidou.mybatisplus.core.parser.SqlInfo;
import com.cn.mybatisStudy.interceptor.NewSqlInterceptor;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Method;

@Intercepts(value = {
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class,
                ResultHandler.class, CacheKey.class, BoundSql.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})

})
public class LogicDeleteInterceptor implements Interceptor {
    AbstractJsqlParser parser;

    public LogicDeleteInterceptor(AbstractJsqlParser parser) {
        this.parser = parser;
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement ms = (MappedStatement) invocation.getArgs()[0];
        if(shouldSkip(ms)) {
            return invocation.proceed();
        }
        Object parameter = invocation.getArgs()[1];
        BoundSql boundSql = ms.getBoundSql(parameter);
        String oldSql = boundSql.getSql();

        SqlInfo sqlInfo = this.parser.parser(ms.getConfiguration().newMetaObject(parameter), boundSql.getSql());

        String newSql = StringUtils.isNoneBlank(sqlInfo.getSql()) ? sqlInfo.getSql() : oldSql;
        //创建新的BoundSql
        BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), newSql, boundSql.getParameterMappings(), boundSql.getParameterObject());
        NewSqlInterceptor.BoundSqlSource boundSqlSource = new NewSqlInterceptor.BoundSqlSource(newBoundSql);
        //创建新的MappedStatement
        MappedStatement newMs = NewSqlInterceptor.copyFromMappedStatement(ms, boundSqlSource);

        for (ParameterMapping pm : boundSql.getParameterMappings()) {
            String property = pm.getProperty();
            if(newBoundSql.hasAdditionalParameter(property)) {
                newBoundSql.setAdditionalParameter(property, boundSql.getAdditionalParameter(property));
            }
        }

        invocation.getArgs()[0] = newMs;
        return invocation.proceed();
    }

    private boolean shouldSkip(MappedStatement ms) {
        //com.cn.mybatisStudy.xml.MyStudyMapper.getUserById
        String name = ms.getId();
        //当前类名 com.cn.mybatisStudy.xml.MyStudyMapper
        String className = name.substring(0, name.lastIndexOf("."));
        //当前方法名 getUserById
        String methodName = name.substring(name.lastIndexOf(".") + 1);
        try {
            Class<?> aClass = Class.forName(className);
            if(aClass.isAnnotationPresent(MultiIgnoreLogicDelete.class)) {
                String[] ignoreMethodNames = aClass.getAnnotation(MultiIgnoreLogicDelete.class).value();
                for (String ignoreMethodName : ignoreMethodNames) {
                    if(methodName.equals(ignoreMethodName)) {
                        return true;
                    }
                }
            }

            //遍历方法
            Method[] methods = aClass.getMethods();
            for (Method m : methods) {
                if(methodName.equals(m.getName())) {
                    return m.isAnnotationPresent(IgnoreLogicDelete.class);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
