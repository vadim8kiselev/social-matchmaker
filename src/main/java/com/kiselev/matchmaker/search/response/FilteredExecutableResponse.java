package com.kiselev.matchmaker.search.response;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
public class FilteredExecutableResponse<Pojo> implements ExecutableResponse<Pojo> {

    private List<Pojo> data;

    public NonFilteredExecutableResponse then() {
        return new NonFilteredExecutableResponse();
    }

    public List<Pojo> execute() {
        return Lists.newArrayList();
    }
}
