package com.kiselev.matchmaker.api.network.vk;

import com.kiselev.matchmaker.api.network.vk.converter.VKEntityConverter;
import com.kiselev.matchmaker.api.network.vk.implementation.VKAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("vk")
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
