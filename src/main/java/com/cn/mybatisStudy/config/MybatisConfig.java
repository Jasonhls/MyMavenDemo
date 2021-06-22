package com.cn.mybatisStudy.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.cn.mybatisStudy.interceptor.logicDelete.LogicDeleteInterceptor;
import com.cn.mybatisStudy.interceptor.logicDelete.LogicDeleteProperties;
import com.cn.mybatisStudy.interceptor.logicDelete.LogicDeleteSqlParser;
import com.cn.mybatisStudy.interceptor.security.SafeSqlInterceptor;
import com.cn.mybatisStudy.interceptor.security.SafeSqlParser;
import com.cn.mybatisStudy.interceptor.security.SafeSqlProperties;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties(value = {SafeSqlProperties.class, LogicDeleteProperties.class})
@ComponentScan(value = {"com.cn.mybatisStudy"})
public class MybatisConfig {

    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        return dataSource;
    }

    @Bean
    public LogicDeleteInterceptor logicDeleteInterceptor(LogicDeleteProperties logicDeleteProperties) {
        LogicDeleteSqlParser parser = new LogicDeleteSqlParser(logicDeleteProperties);
        return new LogicDeleteInterceptor(parser);
    }

    @Bean
    public SafeSqlInterceptor safeSqlInterceptor(SafeSqlProperties safeSqlProperties) {
        SafeSqlParser safeSqlParser = new SafeSqlParser(safeSqlProperties);
        return new SafeSqlInterceptor(safeSqlParser);
    }

    @Bean
    public SqlSessionFactory getSqlSessionFactory(DataSource dataSource, LogicDeleteInterceptor logicDeleteInterceptor,
                                                  SafeSqlInterceptor safeSqlInterceptor) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        //读取mapper 配置文件
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("mybatis/mapper/*.xml");
        sqlSessionFactoryBean.setMapperLocations(resources);

        //配置驼峰命名规则
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        sqlSessionFactoryBean.setConfiguration(configuration);

        //加入自定应的mybatis拦截器
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{logicDeleteInterceptor, safeSqlInterceptor});
        return sqlSessionFactoryBean.getObject();
    }
}
