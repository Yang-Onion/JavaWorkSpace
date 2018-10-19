package com.yangonion.security.sms;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SmsAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private static final String MOBILE_KEY ="mobile";
    private String mobileParameter=MOBILE_KEY;
    private  boolean postOnly=true;

    public  SmsAuthenticationFilter(){
        super(new AntPathRequestMatcher("/login/mobile","POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        if (postOnly && !httpServletRequest.getMethod().equalsIgnoreCase("POST")){
            throw  new AuthenticationServiceException("");
        }
        String  mobile =httpServletRequest.getParameter(mobileParameter);
        if (mobile==null){
            mobile="";
        }
        mobile=mobile.trim();

        SmsAuthenticationToken smsAuthenticationToken=new SmsAuthenticationToken(mobile);
        setDetails(httpServletRequest,smsAuthenticationToken);

        return this.getAuthenticationManager().authenticate(smsAuthenticationToken);
    }

    protected void setDetails(HttpServletRequest request,SmsAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }


    public  void setMobileParameter(String mobileParameter){
        Assert.hasText(mobileParameter,"mobile parameter must not be empty or null");
        this.mobileParameter=mobileParameter;
    }

    public  void setPostOnly(boolean postOnly){
        this.postOnly=postOnly;
    }

    public final  String getMobileParameter(){
        return this.mobileParameter;
    }

}
