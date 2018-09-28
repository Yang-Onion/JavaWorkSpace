package com.yangonion.aop.log.aspect;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.yangonion.aop.log.annotation.Log;
import com.yangonion.aop.log.dao.SysLogDao;
import com.yangonion.aop.log.domain.SysLog;
import com.yangonion.aop.log.util.HttpContextUtils;
import com.yangonion.aop.log.util.IPUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Aspect
@Component
public class LogAspect {
    @Autowired
    private SysLogDao sysLogDao;
    @Pointcut("@annotation(com.yangonion.aop.log.annotation.Log)")
    public void pointcut(){}

    @Around("pointcut()")
    public  Object around(ProceedingJoinPoint point){
        Object result =null;
        long beginTime = System.currentTimeMillis();
        try {
            result = point.proceed();
        }catch (Throwable ex){
            ex.printStackTrace();
        }
        long usedTime = System.currentTimeMillis()-beginTime;
        saveLog(point,usedTime);
        return  result;
    }

    private  void saveLog(ProceedingJoinPoint point,long usedTime){
        MethodSignature methodSignature= (MethodSignature) point.getSignature();
        Method method=methodSignature.getMethod();
        SysLog sysLog=new SysLog();
        Log logAnnotation = method.getAnnotation(Log.class);
        if (logAnnotation !=null){
            //注解上的描述
            sysLog.setOperation(logAnnotation.value());
        }
        String className = point.getTarget().getClass().getName();
        String methodName = methodSignature.getName();
        sysLog.setMethod(className+"."+methodName);

        Object[] params = point.getArgs();
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = u.getParameterNames(method);

        if (params!=null && paramNames !=null){
            String paramString="";
            for (int i=0;i<params.length;i++){
                paramString +=" "+paramNames[i]+":"+params[i];
            }
            sysLog.setParams(paramString);
        }

        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        sysLog.setIp(IPUtils.getIpAddress(request));

        sysLog.setUsername("Yang-Onion");
        sysLog.setTime((int)usedTime);
        sysLog.setCreateTime(new Date());
        sysLogDao.saveSysLog(sysLog);
    }
}
