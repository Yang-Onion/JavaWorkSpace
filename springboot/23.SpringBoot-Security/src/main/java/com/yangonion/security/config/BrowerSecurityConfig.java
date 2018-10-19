package com.yangonion.security.config;

import com.sun.scenario.effect.impl.prism.PrReflectionPeer;
import com.yangonion.security.filter.ValidateCodeFilter;
import com.yangonion.security.handler.AuthFailHandler;
import com.yangonion.security.handler.AuthSuccessHandler;
import com.yangonion.security.service.UserService;
import com.yangonion.security.sms.SmsCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;

import javax.sql.DataSource;

@Configuration
public class BrowerSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthSuccessHandler authSuccessHandler;
    @Autowired
    private AuthFailHandler authFailHandler;

    @Autowired
    private ValidateCodeFilter validateCodeFilter;

    @Autowired
    private SmsCodeFilter smsCodeFilter;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(smsCodeFilter,UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/login")
                .successHandler(authSuccessHandler)
                .failureHandler(authFailHandler)
                .and()
                    .rememberMe()
                    .tokenRepository(persistentTokenRepository())
                    .tokenValiditySeconds(3600)
                    .userDetailsService(userService)
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require","/login.html","/css/**","/code/image","/code/sms").permitAll()
                .anyRequest()
                .authenticated().and().csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository= new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        jdbcTokenRepository.setCreateTableOnStartup(false);
        return jdbcTokenRepository;
    }

}
