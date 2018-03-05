package com.kiselev.matchmaker.api.network.vk;

import com.kiselev.matchmaker.api.network.vk.aspect.VKCallTimeoutAspect;
import com.kiselev.matchmaker.api.network.vk.aspect.VKHandleAPIExceptionAspect;
import com.kiselev.matchmaker.api.network.vk.configuration.VKAPIConfiguration;
import com.kiselev.matchmaker.api.network.vk.converter.VKEntityConverter;
import com.kiselev.matchmaker.api.network.vk.implementation.VKAPI;
import com.kiselev.matchmaker.api.network.vk.implementation.internal.VKAPIInternalProvider;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("vk")
@EnableCaching
@Configuration
public class VKConfiguration {

    @Bean
    public VKAPIConfiguration vkapiConfiguration() {
        return new VKAPIConfiguration();
    }

    @Bean
    @Primary
    public VKAPI vkapi() {
        return new VKAPI();
    }

    @Bean
    public VKAPIInternalProvider vkapiInternal() {
        return new VKAPIInternalProvider();
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
    public VKHandleAPIExceptionAspect vkHandleAPIExceptionAspect() {
        return new VKHandleAPIExceptionAspect();
    }
}
