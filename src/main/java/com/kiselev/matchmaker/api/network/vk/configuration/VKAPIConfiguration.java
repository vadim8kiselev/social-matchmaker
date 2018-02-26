package com.kiselev.matchmaker.api.network.vk.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
public class VKAPIConfiguration {

    @Value("${vk.api.clientId}")
    private Integer clientId;

    @Value("${vk.api.clientSecret}")
    private String clientSecret;

    @Value("${vk.api.redirectUri}")
    private String redirectUri;

    @Value("${vk.api.secretCode}")
    private String secretCode;
}
