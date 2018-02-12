package com.kiselev.matchmaker.search.condition.applier.implementation;

import com.kiselev.matchmaker.api.model.Group;
import com.kiselev.matchmaker.search.condition.Condition;
import com.kiselev.matchmaker.search.condition.applier.ConditionApplier;

import java.util.List;

public class GroupConditionApplier implements ConditionApplier<Group> {

    @Override
    public List<Group> apply(List<Group> groups, Condition condition) {
        // TODO: filter
        return groups;
    }
}
