package com.kiselev.matchmaker.search.condition.applier.implementation;

import com.kiselev.matchmaker.api.model.User;
import com.kiselev.matchmaker.search.condition.Condition;
import com.kiselev.matchmaker.search.condition.applier.ConditionApplier;

import java.util.List;

public class UserConditionApplier implements ConditionApplier<User> {

    @Override
    public List<User> apply(List<User> users, Condition condition) {
        // TODO: filter
        return users;
    }
}
