package com.yangonion.filterandinterceptor.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.spi.http.HttpHandler;

@Component
public class TimeInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        System.out.println("=================拦截之前==================");
        request.setAttribute("startTime",System.currentTimeMillis());
        System.out.println(String.format("==================className:%s==================",((HandlerMethod)handler).getBean().getClass().getName()));
        System.out.println(String.format("==================mathodName:%s==================",((HandlerMethod)handler).getMethod().getName()));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        System.out.println("==================开始拦截==================");
        Long start = (Long) request.getAttribute("startTime");
        Long endTime = System.currentTimeMillis();
        System.out.println(String.format("==================耗时:%s==================",(endTime-start)));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        System.out.println("处理拦截之后");
        Long start = (Long) request.getAttribute("startTime");
        System.out.println("【拦截器】耗时 " + (System.currentTimeMillis()- start));
        System.out.println("异常信息 " + ex);
    }
}
