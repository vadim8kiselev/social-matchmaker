package com.kiselev.matchmaker.api.network.vk.aspect;

import com.kiselev.matchmaker.api.network.vk.utils.VKUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class VKCallTimeoutAspect {

    @Pointcut("execution(* com.kiselev.matchmaker.api.network.vk.implementation.VKAPI.* (..))")
    public void callApi() {
    }

    @Before("callApi()")
    public void sleepAfterCallVKAPI() {
        VKUtils.timeout();
    }
}
