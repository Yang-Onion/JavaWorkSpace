package com.yangonion.security.sms;

import com.yangonion.security.service.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class SmsAuthenticationProvider implements AuthenticationProvider {

    private UserService userService;

    public UserService getUserDetailService(){
        return userService;
    }

    public  void setUserDetailService(UserService userService){

        this.userService=userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        SmsAuthenticationToken authenticationToken= (SmsAuthenticationToken) authentication;
        UserDetails userDetails=userService.loadUserByUsername((String) authenticationToken.getPrincipal());
        if (userDetails==null){
            throw  new InternalAuthenticationServiceException("未找到与该手机号对应的用户");
        }
        SmsAuthenticationToken authenticationResult = new SmsAuthenticationToken(userDetails,userDetails.getAuthorities());
        authenticationResult.setDetails(authenticationToken.getDetails());

        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return SmsAuthenticationToken.class.isAssignableFrom(aClass);
    }
}
