package com.kiselev.matchmaker.search.operation.implementation;

import com.kiselev.matchmaker.api.model.Group;
import com.kiselev.matchmaker.search.condition.Condition;
import com.kiselev.matchmaker.search.condition.applier.implementation.GroupConditionApplier;
import com.kiselev.matchmaker.search.operation.FilterableOperation;
import com.kiselev.matchmaker.search.operation.PerformableOperation;
import com.kiselev.matchmaker.search.operation.RepeatableOperation;
import com.kiselev.matchmaker.search.operation.StatefulOperation;
import com.kiselev.matchmaker.search.service.implementation.GroupSearch;
import com.kiselev.matchmaker.search.service.state.LoopFabric;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
public class GroupOperation implements StatefulOperation<Group>, FilterableOperation<Group>, RepeatableOperation<GroupSearch>, PerformableOperation<Group> {

    @Autowired
    private LoopFabric loopFabric;

    @Autowired
    private GroupConditionApplier groupConditionApplier;

    private List<Group> groups;

    @Override
    public GroupOperation from(List<Group> groups) {
        this.groups = groups;
        return this;
    }

    public GroupOperation where(Condition<Group> condition) {
        return from(groupConditionApplier.apply(groups, condition));
    }

    public GroupSearch then() {
        return loopFabric.createSearch().fromGroups(groups.stream()
                .map(Group::getId)
                .collect(Collectors.toList()));
    }

    @Override
    public List<Group> perform() {
        return groups;
    }
}
