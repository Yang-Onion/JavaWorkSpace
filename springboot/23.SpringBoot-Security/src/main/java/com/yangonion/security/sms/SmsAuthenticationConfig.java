package com.yangonion.security.sms;

import com.yangonion.security.handler.AuthFailHandler;
import com.yangonion.security.handler.AuthSuccessHandler;
import com.yangonion.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class SmsAuthenticationConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private AuthSuccessHandler authSuccessHandler;

    @Autowired
    private AuthFailHandler authFailHandler;

    @Autowired
    private UserService userService;

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        SmsAuthenticationFilter smsAuthenticationFilter= new SmsAuthenticationFilter();
        smsAuthenticationFilter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
        smsAuthenticationFilter.setAuthenticationSuccessHandler(authSuccessHandler);
        smsAuthenticationFilter.setAuthenticationFailureHandler(authFailHandler);
        SmsAuthenticationProvider smsAuthenticationProvider= new SmsAuthenticationProvider();
        smsAuthenticationProvider.setUserDetailsService(userService);

        builder.authenticationProvider(smsAuthenticationProvider)
                .addFilterAfter(smsAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
