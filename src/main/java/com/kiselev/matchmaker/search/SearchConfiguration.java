package com.kiselev.matchmaker.search;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
@Configuration
public class SearchConfiguration {

    @Bean
    @Scope(scopeName = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public UserSearch userSearch() {
        return new UserSearch();
    }

    @Bean
    @Scope(scopeName = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public PostSearch postSearch() {
        return new PostSearch();
    }

    @Bean
    @Scope(scopeName = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public CommunitySearch communitySearch() {
        return new CommunitySearch();
    }
}
