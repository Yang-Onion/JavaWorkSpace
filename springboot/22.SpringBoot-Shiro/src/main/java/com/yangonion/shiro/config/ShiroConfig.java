package com.yangonion.shiro.config;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){

        ShiroFilterFactoryBean shiroFilterFactoryBean= new ShiroFilterFactoryBean();

        shiroFilterFactoryBean.setSecurityManager(securityManager);;
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        LinkedHashMap<String,String> filterChainDefinitionMap =new LinkedHashMap<>();
        //静态资源,不需要拦截
        filterChainDefinitionMap.put("/css/**","anno");
        filterChainDefinitionMap.put("/js/**","anno");
        filterChainDefinitionMap.put("/fonts/**","anno");
        filterChainDefinitionMap.put("/img/**","anno");
        //druid监控页面不拦截
        filterChainDefinitionMap.put("/druid/**","anno");
        filterChainDefinitionMap.put("/logout","logout");
        filterChainDefinitionMap.put("/","anno");

        //除上以外所有url都必须认证通过才可以访问，未通过认证自动访问LoginUrl
        filterChainDefinitionMap.put("/**","authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return  shiroFilterFactoryBean;
    }
    @Bean
    public SecurityManager securityManager(){
        DefaultSecurityManager securityManager= new DefaultSecurityManager();
        securityManager.setRealm(shiroRealm());
        return  securityManager;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return  new LifecycleBeanPostProcessor();
    }


    @Bean
    public ShiroRealm shiroRealm(){
        ShiroRealm shiroRealm=new ShiroRealm();
        return shiroRealm;
    }
}
