package com.kiselev.matchmaker.api.network.vk.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
public class VKAPIConfiguration {

    @Value("${vk.api.client-id}")
    private Integer clientId;

    @Value("${vk.api.client-secret}")
    private String clientSecret;

    @Value("${vk.api.redirect-uri}")
    private String redirectUri;

    @Value("${vk.api.secret-code}")
    private String secretCode;

    // Optional
    @Value("${vk.api.user-id}")
    private Integer userId;

    @Value("${vk.api.token}")
    private String token;
}
