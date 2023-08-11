package com.qinxxz.encipher.core;


import jakarta.servlet.*;

import java.io.IOException;

/**
 * 请求过滤器
 *
 * @author qinxianzhong
 * @since 2023/8/11 9:46:46
 */
public class RequestFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
