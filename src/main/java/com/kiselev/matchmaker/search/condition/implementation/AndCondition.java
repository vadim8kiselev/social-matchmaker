package com.kiselev.matchmaker.search.condition.implementation;

import com.google.common.collect.Lists;
import com.kiselev.matchmaker.api.model.Entity;
import com.kiselev.matchmaker.search.condition.SearchCondition;

import java.util.List;

public class AndCondition implements SearchCondition {

    private List<SearchCondition> conditions;

    private AndCondition(List<SearchCondition> conditions) {
        this.conditions = conditions;
    }

    public static AndCondition of(SearchCondition... conditions) {
        return new AndCondition(Lists.newArrayList(conditions));
    }

    @Override
    public boolean isCompleted(Entity entity) {
        return conditions.stream()
                .allMatch(condition -> condition.isCompleted(entity));
    }
}
