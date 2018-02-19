package com.kiselev.matchmaker.search.condition;

import com.kiselev.matchmaker.search.condition.applier.ConditionApplier;
import com.kiselev.matchmaker.search.condition.matcher.ConditionMatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SearchConditionConfiguration {

    @Bean
    public ConditionApplier conditionApplier() {
        return new ConditionApplier();
    }

    @Bean
    public ConditionMatcher conditionMatcher() {
        return new ConditionMatcher();
    }
}
