package com.yangonion.jsoup.filter;


import com.sun.org.apache.xml.internal.security.Init;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.jsoup.helper.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XSSFilter implements Filter {

    private  static Logger logger = LoggerFactory.getLogger(XSSFilter.class);
    private  static  boolean IS_INCLUDE_RICH_TEXT =false;

    public List<String> excludes = new ArrayList<String>();

    @Override
    public void init(FilterConfig filterConfig) {
        logger.info("---xss filter inital---");
        String isIncludeRichText=filterConfig.getInitParameter("isIncludeRichText");
        if (!StringUtils.isEmpty(isIncludeRichText)){
            IS_INCLUDE_RICH_TEXT=isIncludeRichText.toLowerCase().equals("true")?true:false;
        }
        String temp = filterConfig.getInitParameter("excludes");
        if (temp != null){
            String[] url =temp.split(",");
            for (int i=0;i<url.length;i++){
                excludes.add(url[i]);
            }
        }

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws  IOException,ServletException {
        try {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response =(HttpServletResponse) servletResponse;
            if (handleExcludeUrl(request,response)){
                filterChain.doFilter(request,response);
                return;
            }
            XssHttpServletRequestWrapper xssHttpServletRequestWrapper=new XssHttpServletRequestWrapper(request,IS_INCLUDE_RICH_TEXT);
            filterChain.doFilter(xssHttpServletRequestWrapper,response);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {

    }

    private  boolean handleExcludeUrl(HttpServletRequest servletRequest, HttpServletResponse servletResponse){

        boolean result =false;

        if ( excludes==null || excludes.isEmpty()){
            return  result;
        }
        String url = servletRequest.getServletPath();
        for (String pattern:excludes){
            Pattern p = Pattern.compile("^"+pattern);
            Matcher matcher = p.matcher(url);
            if (matcher.find()){
                result= true;
            }
            result =   false;
        }
        return  result;
    }
}
