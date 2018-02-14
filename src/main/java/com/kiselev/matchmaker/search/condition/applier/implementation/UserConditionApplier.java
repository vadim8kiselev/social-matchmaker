package com.kiselev.matchmaker.search.condition.applier.implementation;

import com.kiselev.matchmaker.api.model.User;
import com.kiselev.matchmaker.search.condition.Condition;
import com.kiselev.matchmaker.search.condition.applier.ConditionApplier;

import java.util.List;
import java.util.stream.Collectors;

public class UserConditionApplier implements ConditionApplier<User> {

    @Override
    public List<User> apply(List<User> users, Condition<User> condition) {
        return users.stream()
                .filter(condition::isCompleted)
                .collect(Collectors.toList());
    }
}
