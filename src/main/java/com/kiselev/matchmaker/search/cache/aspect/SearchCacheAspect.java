package com.kiselev.matchmaker.search.cache.aspect;

import com.kiselev.matchmaker.search.cache.SearchCache;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Aspect
public class SearchCacheAspect {

    @Autowired
    private SearchCache cache;

    @Pointcut("execution(* com.kiselev.matchmaker.search.service.target.FromSearch.from* (..))")
    public void startNode() {
    }

    @Pointcut("execution(* com.kiselev.matchmaker.search.service.target.implementation.*.* (..))  && !finishNode()")
    public void cacheNode() {
    }

    @Pointcut("execution(* com.kiselev.matchmaker.search.service.target.implementation.*.perform (..))")
    public void finishNode() {
    }

    @Around("startNode()")
    public Object startCaching(ProceedingJoinPoint joinPoint) throws Throwable {
        String operation = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();

        log("Start", operation, arguments);

        Object result = joinPoint.proceed();
        cache.initializeQuery(operation, arguments, result);
        return result;
    }

    @Around("cacheNode()")
    public Object cacheNodes(ProceedingJoinPoint joinPoint) throws Throwable {
        String operation = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();

        log("Collect", operation, arguments);

        Object result = joinPoint.proceed();
        cache.collectQueryResults(operation, arguments, result);
        return result;
    }

    @Around("finishNode()")
    public Object completeCaching(ProceedingJoinPoint joinPoint) throws Throwable {
        String operation = joinPoint.getSignature().getName();

        log("Perform", operation);

        Object result = joinPoint.proceed();
        cache.completeQuery(result);
        return result;
    }

    private void log(String action, String operation) {
        System.out.println("Perform: " + operation + "()");
    }

    private void log(String action, String operation, Object[] arguments) {
        List<String> args = Arrays.stream(arguments).map(Object::toString).collect(Collectors.toList());
        System.out.println(action + ": " + operation + "(" + String.join(", ", args) + ")");
    }
}
