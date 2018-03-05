package com.kiselev.matchmaker.search;

import com.kiselev.matchmaker.search.cache.CacheConfiguration;
import com.kiselev.matchmaker.search.condition.SearchConditionConfiguration;
import com.kiselev.matchmaker.search.service.Search;
import com.kiselev.matchmaker.search.service.target.FromSearch;
import com.kiselev.matchmaker.search.service.target.factory.SearchFactory;
import com.kiselev.matchmaker.search.service.target.general.GeneralGroupSearch;
import com.kiselev.matchmaker.search.service.target.general.GeneralPostSearch;
import com.kiselev.matchmaker.search.service.target.general.GeneralUserSearch;
import com.kiselev.matchmaker.search.service.target.implementation.GroupSearch;
import com.kiselev.matchmaker.search.service.target.implementation.PostSearch;
import com.kiselev.matchmaker.search.service.target.implementation.UserSearch;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
@Configuration
@Import({SearchConditionConfiguration.class, CacheConfiguration.class})
public class SearchConfiguration {

    @Bean
    @Scope(scopeName = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Search search() {
        return new FromSearch();
    }

    @Bean
    @Scope(scopeName = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public SearchFactory searchFactory() {
        return new SearchFactory();
    }

    @Bean
    @Scope("prototype")
    public GeneralUserSearch userSearch() {
        return new UserSearch();
    }

    @Bean
    @Scope("prototype")
    public GeneralPostSearch postSearch() {
        return new PostSearch();
    }

    @Bean
    @Scope("prototype")
    public GeneralGroupSearch groupSearch() {
        return new GroupSearch();
    }
}
