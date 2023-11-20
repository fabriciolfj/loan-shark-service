package com.github.loanshark.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
@Slf4j
@Order(1)
public class LoggingControllerAspect {

    @Pointcut("execution(* com.github.loanshark.entrypoints.controller.LoanController.*(..))")
    public void logController() { }

    @Before("logController()")
    public void logBefore(final JoinPoint joinpoint) {
        var name = joinpoint.getSignature().getName();
        var args = Arrays.asList(joinpoint.getArgs());

        log.info("the method {}, begins with {} ", name, args);
    }
}
