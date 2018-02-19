package com.kiselev.matchmaker.search.service.concept.theory;

import com.kiselev.matchmaker.search.condition.SearchCondition;

@FunctionalInterface
public interface FilterableSearch<Search extends FilterableSearch> {

    Search where(SearchCondition condition);
}
