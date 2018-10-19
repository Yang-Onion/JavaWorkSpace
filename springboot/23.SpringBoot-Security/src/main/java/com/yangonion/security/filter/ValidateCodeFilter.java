package com.yangonion.security.filter;

import com.yangonion.security.controller.ValidateController;
import com.yangonion.security.exception.ValidateCodeException;
import com.yangonion.security.handler.AuthFailHandler;
import com.yangonion.security.model.ImageCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;




import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.imageio.ImageIO;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class ValidateCodeFilter extends OncePerRequestFilter {


    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    private SessionStrategy sessionStrategy=new HttpSessionSessionStrategy();


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (StringUtils.endsWithIgnoreCase("/login",httpServletRequest.getRequestURI())
                && StringUtils.equalsIgnoreCase(httpServletRequest.getMethod(),"post")
        ) {
            try {
                validateCode(new ServletWebRequest(httpServletRequest));
            } catch (ValidateCodeException ex) {
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, ex);
                return;
            }
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    private  void validateCode(ServletWebRequest servletWebRequest) throws ServletRequestBindingException {

        ImageCode  seesionImageCode = (ImageCode) sessionStrategy.getAttribute(servletWebRequest, ValidateController.SESSSION_IMAGE_CODE_KEY);
        String requestCode = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(),"imageCode");

        if (StringUtils.isEmpty(requestCode)){
            throw  new ValidateCodeException("验证码不能不空！");
        }
        if (seesionImageCode==null){
            throw  new ValidateCodeException("验证码不存在！");
        }
         if (!StringUtils.equalsIgnoreCase(requestCode,seesionImageCode.getCode())){
            throw  new ValidateCodeException("验证码错误！");
        }
        if (seesionImageCode.getExpireTime().isBefore(LocalDateTime.now()) ){
            throw  new ValidateCodeException("验证码已过期！");
        }
    }
}
