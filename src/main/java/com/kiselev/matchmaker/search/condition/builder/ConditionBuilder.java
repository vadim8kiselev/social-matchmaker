package com.kiselev.matchmaker.search.condition.builder;

import com.kiselev.matchmaker.api.model.Mapper;
import com.kiselev.matchmaker.search.condition.SearchCondition;
import com.kiselev.matchmaker.search.condition.implementation.AndCondition;
import com.kiselev.matchmaker.search.condition.implementation.EqualCondition;
import com.kiselev.matchmaker.search.condition.implementation.OrCondition;

public class ConditionBuilder {

    public static SearchCondition all(SearchCondition... conditions) {
        return AndCondition.of(conditions);
    }

    public static SearchCondition any(SearchCondition... conditions) {
        return OrCondition.of(conditions);
    }

    public static SearchCondition equal(Mapper constant, String value) {
        return EqualCondition.equal(constant, value);
    }

    public static SearchCondition contains(Mapper constant, String value) {
        return EqualCondition.contains(constant, value);
    }
}
