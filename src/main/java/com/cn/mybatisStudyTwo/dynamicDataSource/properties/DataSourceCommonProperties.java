package com.cn.mybatisStudyTwo.dynamicDataSource.properties;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.annotation.DbType;
import lombok.Data;

@Data
public abstract class DataSourceCommonProperties {

    /**
     * 以下属性值全部通过配置取值，否则取默认值
     */

    public String url;
    public String username;
    public String password;
    //默认根据url自动识别，这一项可不配，如果不配置druid会根据url自动识别dbType，然后选择相应的driverClassName
    public String driverClassName;

    //默认为0，初始化时建立物理连接的个数。初始化发生在显示调用init方法，或者第一次调用getConnection时
    private Integer initialSize = 5;
    //最小连接池数量
    private Integer minIdle = 10;
    //默认为8，已经不在使用，配置了也没用
    private Integer maxIdle;
    //默认为8，最大连接池数量
    private Integer maxActive = 20;
    //获取连接时最大等待时间，单位毫秒。配置了maxWait之后，缺省启用公平锁，并发效率会有所下降，如果需要可通过
    //配置useUnfairLock属性为true使用非公平锁
    private Integer maxWait = 60000;
    //有两个含义：
    //1)Destroy线程会检测连接的间隔时间，如果连接空闲时间大于等于minEvictableIdleTimeMillis则关闭物理连接。
    //2)testWhileIdle的判断已经，详细看testWhileIdle属性的说明
    private Integer timeBetweenEvictionRunsMillis = 2000;
    //连接保持空闲而不被驱逐的最小时间
    private long minEvictableIdleTimeMillis = 600000;
    //连接保持空闲而不被驱逐的最大时间
    private long maxEvictableIdleTimeMillis = 900000;
    //连接池中的minIdle数量以内的连接，空闲时间超过minEvictableIdleTimeMillis，则会执行keepAlive操作。
    private Boolean keepAlive=true;
    //用来检测连接是否有效的sql，要求时一个查询语句，常用 select 'x'。如果validationQuery为null，testOnBorrow、
    //testOnReturn、testWhileIdle都不会起作用。
    private String validationQuery;
    //建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，
    //执行validationQuery检测连接是否有效
    private Boolean testWhileIdle = true;
    //申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。
    private Boolean testOnBorrow = false;
    //归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。
    private Boolean testOnReturn = false;
    //默认为false，是否缓存preparedStatement，也就是PSCache。PSCache对支持游标的数控提升巨大，比如说oracle。在mysql下建议关闭
    private Boolean poolPreparedStatements = true;
    //默认为-1，要启用PSCache，必须配置大于0，当大于0时，poolPreparedStatements自动触发修改为true。在Druid中，不会存在Oracle下
    //PSCache占用过多的问题，可以把这个数值配置大一些，比如说100。
    private Integer maxPoolPreparedStatementPerConnectionSize = 20;

    private String dataSourceName;

    public void config(DruidDataSource dataSource) {
        determineProperties();
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driverClassName);

        dataSource.setInitialSize(initialSize);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxActive(maxActive);
        dataSource.setMaxWait(maxWait);
        dataSource.setKeepAlive(keepAlive);
        dataSource.setValidationQuery(getValidationQueryByUrl(validationQuery));
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        dataSource.setMaxEvictableIdleTimeMillis(maxEvictableIdleTimeMillis);
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnReturn(testOnReturn);
        dataSource.setPoolPreparedStatements(poolPreparedStatements);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
    }

    public void config(DruidDataSource dataSource, DataSourceCommonProperties properties) {
        determineProperties();
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());
        dataSource.setUrl(properties.getUrl());
        dataSource.setDriverClassName(properties.getDriverClassName());

        dataSource.setInitialSize(properties.getInitialSize());
        dataSource.setMinIdle(properties.getMinIdle());
        dataSource.setMaxActive(properties.getMaxActive());
        dataSource.setMaxWait(properties.getMaxWait());
        dataSource.setKeepAlive(properties.getKeepAlive());
        dataSource.setValidationQuery(getValidationQueryByUrl(properties.getValidationQuery()));
        dataSource.setTimeBetweenEvictionRunsMillis(properties.getTimeBetweenEvictionRunsMillis());
        dataSource.setMinEvictableIdleTimeMillis(properties.getMinEvictableIdleTimeMillis());
        dataSource.setMaxEvictableIdleTimeMillis(properties.getMaxEvictableIdleTimeMillis());
        dataSource.setTestWhileIdle(properties.getTestWhileIdle());
        dataSource.setTestOnBorrow(properties.getTestOnBorrow());
        dataSource.setTestOnReturn(properties.getTestOnReturn());
        dataSource.setPoolPreparedStatements(properties.getPoolPreparedStatements());
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(properties.getMaxPoolPreparedStatementPerConnectionSize());
    }

    public abstract void determineProperties();

    private String getValidationQueryByUrl(String url) {
        if(url.contains(DbType.ORACLE.name())) {
            return "select 1 from dual";
        }else {
            return "select 1";
        }
    }

}
