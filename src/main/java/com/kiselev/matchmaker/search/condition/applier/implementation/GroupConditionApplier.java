package com.kiselev.matchmaker.search.condition.applier.implementation;

import com.kiselev.matchmaker.api.model.Group;
import com.kiselev.matchmaker.search.condition.Condition;
import com.kiselev.matchmaker.search.condition.applier.ConditionApplier;

import java.util.List;
import java.util.stream.Collectors;

public class GroupConditionApplier implements ConditionApplier<Group> {

    @Override
    public List<Group> apply(List<Group> groups, Condition<Group> condition) {
        return groups.stream()
                .filter(condition::isCompleted)
                .collect(Collectors.toList());
    }
}
