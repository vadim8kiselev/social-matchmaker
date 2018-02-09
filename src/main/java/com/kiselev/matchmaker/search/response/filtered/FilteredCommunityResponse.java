package com.kiselev.matchmaker.search.response.filtered;

import com.google.common.collect.Lists;
import com.kiselev.matchmaker.api.model.Community;
import com.kiselev.matchmaker.search.response.ExecutableResponse;
import com.kiselev.matchmaker.search.response.NonFilteredExecutableResponse;

import java.util.List;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
public class FilteredCommunityResponse implements ExecutableResponse<Community> {

    private List<Community> data;

    public NonFilteredExecutableResponse then() {
        return new NonFilteredExecutableResponse();
    }

    public List<Community> execute() {
        return Lists.newArrayList();
    }
}
