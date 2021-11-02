package com.Spring.stud.demo.core.utils.AOP;


import lombok.Data;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Data
public class AppLoggingAspect {
    private final UtilService utilService;

    @Around("execution(public * com.Spring.stud.demo.core.services.*.*(..))")
    public Object methodProfiling(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("----");
        System.out.println(proceedingJoinPoint.toShortString());
        String[] execMethodName = proceedingJoinPoint.toShortString().split("\\.");
        String buff = execMethodName[0];
        String serviceName = buff.substring(10);
        System.out.println("----");
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        utilService.addServiceToStatistics(serviceName, duration);
        return out;
    }
}
