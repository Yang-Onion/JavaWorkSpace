package com.yangonion.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthFailHandler implements AuthenticationFailureHandler {

    //private RequestCache requestCache= new HttpSessionRequestCache();
    //private RedirectStrategy redirectStrategy= new DefaultRedirectStrategy();

    @Autowired
    private ObjectMapper mapper;
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException,ServletException {
        //SavedRequest savedRequest= requestCache.getRequest(httpServletRequest,httpServletResponse);
        //redirectStrategy.sendRedirect(httpServletRequest,httpServletResponse,"/login.html");
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().write(mapper.writeValueAsString(e.getMessage()));
    }
}
