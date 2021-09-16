package com.cn.servlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Jason on 2018/11/12.
 */
@Configuration
public class TestConfig {

    /*添加自定义的servlet*/
    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        return new ServletRegistrationBean(new DemoServlet(),"/demo");
    }

}
