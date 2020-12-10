package com.thesong.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Author thesong
 * @Date 2020/12/10 11:31
 * @Version 1.0
 * @Describe
 */

@Aspect
@Component
public class GetAspectJ {

    @Pointcut("execution(* com.thesong.aop.service.IUserService.*(..))")
    public void point(){}


//    @Before("point()")
//    public void getBefore(){
//        System.out.println("方法before...");
//    }
//
//    @After("point()")
//    public void getAfter(){
//        System.out.println("方法after...");
//    }
//
    @Around("point()")
    public Object getAround(ProceedingJoinPoint pjp) throws Throwable {
        Method method = pjp.getTarget().getClass().getMethod(pjp.getSignature().getName());
        System.out.println(method.getName().equals(pjp.getSignature().getName()));
        return pjp.proceed();

    }
}
