package com.yangonion.filterandinterceptor.config;

import com.yangonion.filterandinterceptor.filter.TimeFilter;
import com.yangonion.filterandinterceptor.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebCofig implements WebMvcConfigurer {


    @Autowired
    private TimeInterceptor timeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor).addPathPatterns("/**/*");
    }


    //方法二:通过FilterRegistrationBean注册过滤器,设置filter影响的controller或action
    @Bean
    public FilterRegistrationBean timeFilter(){

        FilterRegistrationBean filterRegistrationBean= new FilterRegistrationBean();
        TimeFilter timeFilter=new TimeFilter();
        filterRegistrationBean.setFilter(timeFilter);

        List<String> urlList = new ArrayList<>();
        urlList.add("/*");

        filterRegistrationBean.setUrlPatterns(urlList);
        return  filterRegistrationBean;

    }
}
