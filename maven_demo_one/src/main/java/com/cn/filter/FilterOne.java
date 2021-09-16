package com.cn.filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Order(value = 1)
@Component
public class FilterOne implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String who = filterConfig.getInitParameter("who");
        System.out.println("Filter1初始化了,获取了who参数是" + who);

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Filter1拦截客户端向服务器发的请求");
        // 把Servlet请求和回应传给过滤链上的下一过滤器
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("Filter1拦截服务器向客户端发的响应");
    }

    @Override
    public void destroy() {
        System.out.println("Filter1要被移除了");
    }
}
