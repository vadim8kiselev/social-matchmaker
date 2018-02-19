package com.kiselev.matchmaker.search.cache;

import com.kiselev.matchmaker.search.cache.aspect.SearchCacheAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SearchCacheConfiguration {

    @Bean
    public SearchCache cache() {
        return new SearchCache();
    }

    @Bean
    public SearchCacheAspect searchCacheAspect() {
        return new SearchCacheAspect();
    }
}
