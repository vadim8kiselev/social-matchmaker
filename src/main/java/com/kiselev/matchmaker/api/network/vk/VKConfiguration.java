package com.kiselev.matchmaker.api.network.vk;

import com.kiselev.matchmaker.api.network.vk.aspect.VKCallTimeoutAspect;
import com.kiselev.matchmaker.api.network.vk.converter.VKEntityConverter;
import com.kiselev.matchmaker.api.network.vk.implementation.VKAPI;
import com.kiselev.matchmaker.api.network.vk.implementation.internal.VKAPIInternal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("vk")
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
}
