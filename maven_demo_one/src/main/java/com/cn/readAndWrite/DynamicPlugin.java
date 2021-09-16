package com.cn.readAndWrite;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.keygen.SelectKeyGenerator;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

@Intercepts({
        @Signature(type = Executor.class,method = "update",args = {MappedStatement.class,Object.class}),
        @Signature(type = Executor.class,method = "query",args = {MappedStatement.class,Object.class, RowBounds.class, ResultHandler.class})
})
@Component
public class DynamicPlugin implements Interceptor {
    private Logger log = LoggerFactory.getLogger(DynamicPlugin.class);

    private static final String REGEX = ".*insert$|.*delete$|.*update$";

    private static final Map<String,DynamicDataSourceGlobal> cacheMap = new ConcurrentHashMap<>();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] objects = invocation.getArgs();
        MappedStatement ms = (MappedStatement) objects[0];
        DynamicDataSourceGlobal dynamicDataSourceGlobal = null;
        if((dynamicDataSourceGlobal = cacheMap.get(ms.getId())) == null){
            //读方法
            if(ms.getSqlCommandType().equals(SqlCommandType.SELECT)){
                if(ms.getId().contains(SelectKeyGenerator.SELECT_KEY_SUFFIX)){
                    dynamicDataSourceGlobal = DynamicDataSourceGlobal.WRITE;
                }else{
                    BoundSql boundSql = ms.getSqlSource().getBoundSql(objects[1]);
                    String sql = boundSql.getSql().toLowerCase(Locale.CHINA)
                            .replaceAll("\\t\\n\\r"," ");
                    if(sql.matches(REGEX)){
                        dynamicDataSourceGlobal = DynamicDataSourceGlobal.WRITE;
                    }else{
                        dynamicDataSourceGlobal = DynamicDataSourceGlobal.READ;
                    }
                }
            }else{
                //写方法
                dynamicDataSourceGlobal = DynamicDataSourceGlobal.WRITE;
            }
            System.out.println("设置方法[{"+ms.getId()+"}] use [{" + dynamicDataSourceGlobal.name() +
                    "}] Strategy,SqlCommandType [{"+ ms.getSqlCommandType().name());
            log.warn("设置方法[{}] use [{}] Strategy,SqlCommandType [{}] ..",ms.getId(),dynamicDataSourceGlobal.name(),
                    ms.getSqlCommandType().name());
            //把id(dao层的方法名)和数据源存入map，下次命中后直接执行
            cacheMap.put(ms.getId(),dynamicDataSourceGlobal);
        }
        DynamicDataSourceHolder.putDataSource(dynamicDataSourceGlobal);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if(target instanceof Executor){
            return Plugin.wrap(target,this);
        }else{
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
