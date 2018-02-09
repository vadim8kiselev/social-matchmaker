package com.kiselev.matchmaker.search;

import com.kiselev.matchmaker.search.response.FilteredExecutableResponse;
import com.kiselev.matchmaker.search.response.NonFilteredExecutableResponse;
import com.kiselev.matchmaker.search.target.CommunitySearch;
import com.kiselev.matchmaker.search.target.PostSearch;
import com.kiselev.matchmaker.search.target.UserSearch;
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
    public Search search() {
        return new Search();
    }

    @Bean
    @Scope("prototype")
    public UserSearch userSearch() {
        return new UserSearch();
    }

    @Bean
    @Scope("prototype")
    public PostSearch postSearch() {
        return new PostSearch();
    }

    @Bean
    @Scope("prototype")
    public CommunitySearch communitySearch() {
        return new CommunitySearch();
    }

    @Bean
    @Scope("prototype")
    public NonFilteredExecutableResponse nonFilteredResponse() {
        return new NonFilteredExecutableResponse();
    }

    @Bean
    @Scope("prototype")
    public FilteredExecutableResponse filteredResponse() {
        return new FilteredExecutableResponse();
    }
}
