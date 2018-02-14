package com.kiselev.matchmaker.search.operation;

import com.kiselev.matchmaker.search.condition.Condition;

@FunctionalInterface
public interface FilterableOperation<Pojo> {

    FilterableOperation where(Condition<Pojo> condition);
}
