package com.kiselev.matchmaker.search.operation;

import java.util.List;

@FunctionalInterface
public interface StatefulOperation<Pojo> {

    StatefulOperation from(List<Pojo> data);
}
