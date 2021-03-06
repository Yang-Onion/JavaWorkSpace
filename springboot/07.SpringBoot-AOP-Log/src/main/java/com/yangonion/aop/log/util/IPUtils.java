package com.yangonion.aop.log.util;

import org.omg.CORBA.PUBLIC_MEMBER;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public class IPUtils {
    /**
     * 获取IP地址
     *
     * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
     * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
     */
    public  static  String getIpAddress(HttpServletRequest request){
        String ip = request.getHeader("x-forwared-for");
        if (ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip)){
            ip=request.getHeader("Proxy-Client-Ip");
        }
        if (ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip)){
            ip=request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip)){
            ip=request.getRemoteAddr();
        }
        return ip;
    }
}
