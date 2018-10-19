package com.yangonion.security.sms;

import com.yangonion.security.controller.ValidateController;
import com.yangonion.security.exception.SmsCodeException;
import com.yangonion.security.exception.ValidateCodeException;
import com.yangonion.security.handler.AuthFailHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SmsCodeFilter extends OncePerRequestFilter {

    @Autowired
    private AuthFailHandler authFailHandler;
    private SessionStrategy sessionStrategy=new HttpSessionSessionStrategy();


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (StringUtils.equalsIgnoreCase("/login/mobile",httpServletRequest.getRequestURI()) &&
            StringUtils.equalsIgnoreCase(httpServletRequest.getMethod(),"post")
        ){
            try {
                checkSmsCode(new ServletWebRequest(httpServletRequest));
            }
            catch (SmsCodeException ex){
                authFailHandler.onAuthenticationFailure(httpServletRequest,httpServletResponse,ex);
            }
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }
    }

    private  void checkSmsCode(ServletWebRequest servletWebRequest) throws ServletRequestBindingException {
        String smsCodeInRequest = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), "smsCode");
        String mobile = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), "mobile");
        SmsCode codeInSession = (SmsCode) sessionStrategy.getAttribute(servletWebRequest, ValidateController.SESSSION_SMS_CODE_KEY + mobile);
        if (StringUtils.isBlank(smsCodeInRequest)) {
            throw new SmsCodeException("验证码不能为空！");
        }
        if (codeInSession == null) {
            throw new SmsCodeException("验证码不存在，请重新发送！");
        }
        if (codeInSession.isExpired()) {
            sessionStrategy.removeAttribute(servletWebRequest, ValidateController.SESSSION_SMS_CODE_KEY + mobile);
            throw new SmsCodeException("验证码已过期，请重新发送！");
        }
        if (!StringUtils.equalsIgnoreCase(codeInSession.getCode(), smsCodeInRequest)) {
            throw new SmsCodeException("验证码不正确！");
        }
        sessionStrategy.removeAttribute(servletWebRequest, ValidateController.SESSSION_SMS_CODE_KEY + mobile);
    }

}
