package com.kiselev.matchmaker.search.condition.applier;

import com.kiselev.matchmaker.search.condition.Condition;

import java.util.List;

@FunctionalInterface
public interface ConditionApplier<Pojo> {

    List<Pojo> apply(List<Pojo> data, Condition<Pojo> condition);
}
