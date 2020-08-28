package com.geekbrains.book.store.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@AllArgsConstructor
@Getter
public class ServiceCounterAspect {
    private Map<String, Integer> serviceToCountMap;

    @Before("execution(public * com.geekbrains.book.store.services..*(..))")
    public void countCall(JoinPoint joinPoint) {
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        serviceToCountMap.put(className, serviceToCountMap.getOrDefault(className, 0) + 1);
    }
}
