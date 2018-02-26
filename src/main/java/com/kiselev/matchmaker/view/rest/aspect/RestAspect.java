package com.kiselev.matchmaker.view.rest.aspect;

import com.kiselev.matchmaker.search.service.target.implementation.GroupSearch;
import com.kiselev.matchmaker.search.service.target.implementation.PostSearch;
import com.kiselev.matchmaker.search.service.target.implementation.UserSearch;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

@Aspect
public class RestAspect {

    @Autowired
    private ApplicationContext applicationContext;

    @Pointcut("execution(* com.kiselev.matchmaker.view.rest.implementation.RestViewController.* (..))" +
            "&& @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void endpoint() {
    }

    @Before("endpoint()")
    public void autowireDeserializedBeans(JoinPoint joinPoint) {
        for (Object arg : joinPoint.getArgs()) {
            if (arg != null) {
                Class<?> type = arg.getClass();
                if (type.isAssignableFrom(UserSearch.class)
                        || type.isAssignableFrom(PostSearch.class)
                        || type.isAssignableFrom(GroupSearch.class)) {
                    applicationContext.getAutowireCapableBeanFactory().autowireBean(arg);
                }
            }
        }
    }
}
