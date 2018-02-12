package com.kiselev.matchmaker.api.implementation.vk;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VKConfiguration {

    @Bean
    public VKAPI vkapi() {
        return new VKAPI();
    }

    @Bean
    public VKEntityConverter vkEntityConverter() {
        return new VKEntityConverter();
    }
}
