package com.kiselev.matchmaker.search.service;

import java.util.List;

@FunctionalInterface
public interface PerformableSearch<Pojo> {

    List<Pojo> perform();
}
