package com.kiselev.matchmaker.search.condition.implementation;

import com.google.common.collect.Lists;
import com.kiselev.matchmaker.api.model.Entity;
import com.kiselev.matchmaker.search.condition.SearchCondition;

import java.util.List;

public class OrCondition implements SearchCondition {

    private List<SearchCondition> conditions;

    private OrCondition(List<SearchCondition> conditions) {
        this.conditions = conditions;
    }

    public static OrCondition of(SearchCondition... conditions) {
        return new OrCondition(Lists.newArrayList(conditions));
    }

    @Override
    public boolean isCompleted(Entity entity) {
        return conditions.stream()
                .anyMatch(condition -> condition.isCompleted(entity));
    }
}
