package com.kiselev.matchmaker.search.condition.applier;

import com.kiselev.matchmaker.api.model.Entity;
import com.kiselev.matchmaker.search.condition.SearchCondition;

import java.util.List;
import java.util.stream.Collectors;

public class ConditionApplier<Pojo extends Entity> {

    public List<Pojo> apply(List<Pojo> entities, SearchCondition condition) {
        if (condition != null) {
            return entities.stream()
                    .filter(condition::isCompleted)
                    .collect(Collectors.toList());
        } else {
            return entities;
        }
    }
}
