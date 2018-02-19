package com.kiselev.matchmaker.search.service.concept.theory;

import com.kiselev.matchmaker.api.model.Entity;

import java.util.List;

@FunctionalInterface
public interface PerformableSearch<Pojo extends Entity> {

    List<Pojo> perform();
}
