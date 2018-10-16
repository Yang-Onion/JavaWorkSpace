package com.yangonion.filterandinterceptor.filter;


import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Date;


@Component

//方法一:直接设置filter影响的controller或action
//@WebFilter(urlPatterns = "/*")

public class TimeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("==================init==================");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("==================filter-begin==================");
        Long startTime = System.currentTimeMillis();
        filterChain.doFilter(servletRequest,servletResponse);

        Long endTime = System.currentTimeMillis();
        long useTime =endTime-startTime;

        System.out.println(String.format("==================useTime:%s==================",useTime));

        System.out.println("==================filter-end==================");

    }

    @Override
    public void destroy() {
        System.out.println("==================destroy==================");
    }
}
