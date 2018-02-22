package com.kiselev.matchmaker.api.network.vk;

import com.kiselev.matchmaker.api.network.vk.aspect.VKCallTimeoutAspect;
import com.kiselev.matchmaker.api.network.vk.converter.VKEntityConverter;
import com.kiselev.matchmaker.api.network.vk.implementation.VKAPI;
import com.kiselev.matchmaker.api.network.vk.implementation.internal.VKAPIInternal;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("vk")
@EnableCaching
@Configuration
public class VKConfiguration {

    @Bean
    @Primary
    public VKAPI vkapi() {
        return new VKAPI();
    }

    @Bean
    public VKAPIInternal vkapiInternal() {
        return new VKAPIInternal();
    }

    @Bean
    public VKEntityConverter vkEntityConverter() {
        return new VKEntityConverter();
    }

    @Bean
    public VKCallTimeoutAspect vkCallAPIAspect() {
        return new VKCallTimeoutAspect();
    }

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("users", "posts", "groups");
    }
}
