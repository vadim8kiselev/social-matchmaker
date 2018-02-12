package com.kiselev.matchmaker.search.operation;

import java.util.List;

@FunctionalInterface
public interface PerformableOperation<Pojo> {

    List<Pojo> perform();
}
