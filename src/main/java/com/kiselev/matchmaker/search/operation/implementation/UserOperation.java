package com.kiselev.matchmaker.search.operation.implementation;

import com.kiselev.matchmaker.api.model.User;
import com.kiselev.matchmaker.search.condition.Condition;
import com.kiselev.matchmaker.search.condition.applier.implementation.UserConditionApplier;
import com.kiselev.matchmaker.search.operation.FilterableOperation;
import com.kiselev.matchmaker.search.operation.PerformableOperation;
import com.kiselev.matchmaker.search.operation.RepeatableOperation;
import com.kiselev.matchmaker.search.operation.StatefulOperation;
import com.kiselev.matchmaker.search.service.implementation.UserSearch;
import com.kiselev.matchmaker.search.service.state.LoopFabric;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
public class UserOperation implements StatefulOperation<User>, FilterableOperation, RepeatableOperation<UserSearch>, PerformableOperation<User> {

    @Autowired
    private LoopFabric loopFabric;

    @Autowired
    private UserConditionApplier userConditionApplier;

    private List<User> users;

    @Override
    public UserOperation from(List<User> users) {
        this.users = users;
        return this;
    }

    public UserOperation where(Condition condition) {
        return from(userConditionApplier.apply(users, condition));
    }

    public UserSearch then() {
        return loopFabric.createUserSearch().from(users.stream()
                .map(User::getId)
                .collect(Collectors.toList()));
    }

    @Override
    public List<User> perform() {
        return users;
    }
}
