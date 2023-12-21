package me.ouohoon.basicreview.global.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Around("execution(public * me.ouohoon.basicreview..*Controller.*(..)) " +
            "|| execution(public * me.ouohoon.basicreview..*Service.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Method Start: {}.{}",
                joinPoint.getSignature().getDeclaringType().getName(), joinPoint.getSignature().getName());
        try {
            Object proceed = joinPoint.proceed();
            log.info("Method End: {}.{}",
                    joinPoint.getSignature().getDeclaringType().getName(), joinPoint.getSignature().getName());
            return proceed;
        } catch (Throwable e) {
            log.error("Method Exception: {}", e.getMessage());
            throw e;
        }
    }
}
