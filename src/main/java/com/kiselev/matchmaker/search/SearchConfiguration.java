package com.kiselev.matchmaker.search;

import com.kiselev.matchmaker.search.condition.applier.implementation.GroupConditionApplier;
import com.kiselev.matchmaker.search.condition.applier.implementation.PostConditionApplier;
import com.kiselev.matchmaker.search.condition.applier.implementation.UserConditionApplier;
import com.kiselev.matchmaker.search.operation.implementation.GroupOperation;
import com.kiselev.matchmaker.search.operation.implementation.PostOperation;
import com.kiselev.matchmaker.search.operation.implementation.UserOperation;
import com.kiselev.matchmaker.search.service.SearchService;
import com.kiselev.matchmaker.search.service.implementation.GroupSearch;
import com.kiselev.matchmaker.search.service.implementation.PostSearch;
import com.kiselev.matchmaker.search.service.implementation.UserSearch;
import com.kiselev.matchmaker.search.service.state.LoopFabric;
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
        return new SearchService();
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
    public GroupSearch groupSearch() {
        return new GroupSearch();
    }

    @Bean
    @Scope("prototype")
    public UserOperation userOperation() {
        return new UserOperation();
    }

    @Bean
    @Scope("prototype")
    public PostOperation postOperation() {
        return new PostOperation();
    }

    @Bean
    @Scope("prototype")
    public GroupOperation groupOperation() {
        return new GroupOperation();
    }

    @Bean
    public LoopFabric loopFabric() {
        return new LoopFabric();
    }

    @Bean
    public UserConditionApplier userConditionApplier() {
        return new UserConditionApplier();
    }

    @Bean
    public PostConditionApplier postConditionApplier() {
        return new PostConditionApplier();
    }

    @Bean
    public GroupConditionApplier groupConditionApplier() {
        return new GroupConditionApplier();
    }
}
