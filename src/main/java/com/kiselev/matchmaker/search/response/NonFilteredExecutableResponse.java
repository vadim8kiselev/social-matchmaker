package com.kiselev.matchmaker.search.response;

import com.google.common.collect.Lists;
import com.kiselev.matchmaker.search.condition.Condition;

import java.util.List;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
public class NonFilteredExecutableResponse<Pojo> implements ExecutableResponse<Pojo> {

    private List<Pojo> data;

    public FilteredExecutableResponse where(Condition condition) {
        return new FilteredExecutableResponse();
    }

    public List<Pojo> execute() {
        return Lists.newArrayList();
    }
}
