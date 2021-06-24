package com.cn.mybatisStudyTwo.dynamicDataSource.druidStat;

import com.alibaba.druid.support.http.StatViewServlet;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.Servlet;

@ConditionalOnClass(value = {Servlet.class})
@EnableConfigurationProperties(value = {DruidStatViewProperties.class})
public class DruidStatViewServletConfiguration {

    /**
     * 注册自定义的Servlet
     * @param config
     * @return
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean(DruidStatViewProperties config) {
        String urlPattern = config.getUrlPattern();
        ServletRegistrationBean servletBean = new ServletRegistrationBean();
        servletBean.setServlet(new StatViewServlet());
        servletBean.addUrlMappings(StringUtils.isNoneBlank(urlPattern) ? urlPattern : "/druid/*");
        if(StringUtils.isNoneBlank(config.getAllow())) {
            servletBean.addInitParameter("allow", config.getAllow());
        }
        if(StringUtils.isNoneBlank(config.getDeny())) {
            servletBean.addInitParameter("deny", config.getDeny());
        }
        if(StringUtils.isNoneBlank(config.getLoginUserName())) {
            servletBean.addInitParameter("loginUserName", config.getLoginUserName());
        }
        if(StringUtils.isNoneBlank(config.getLoginPassword())) {
            servletBean.addInitParameter("loginPassword", config.getLoginPassword());
        }
        if(StringUtils.isNoneBlank(config.getResetEnable())) {
            servletBean.addInitParameter("resetEnable", config.getResetEnable());
        }
        return servletBean;
    }
}
