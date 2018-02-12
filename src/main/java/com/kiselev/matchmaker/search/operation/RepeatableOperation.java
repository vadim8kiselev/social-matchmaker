package com.kiselev.matchmaker.search.operation;

@FunctionalInterface
public interface RepeatableOperation<Search> {

    Search then();
}
