package com.kiselev.matchmaker.search.operation;

import com.kiselev.matchmaker.search.condition.Condition;

@FunctionalInterface
public interface FilterableOperation {

    FilterableOperation where(Condition condition);
}
