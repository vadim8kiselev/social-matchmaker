package com.kiselev.matchmaker.search.response.nonfiltered;

import com.google.common.collect.Lists;
import com.kiselev.matchmaker.api.model.Community;
import com.kiselev.matchmaker.search.condition.Condition;
import com.kiselev.matchmaker.search.response.ExecutableResponse;
import com.kiselev.matchmaker.search.response.FilteredExecutableResponse;

import java.util.List;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
public class NonFilteredCommunityResponse implements ExecutableResponse<Community> {

    private List<Community> communities;

    public NonFilteredCommunityResponse(List<Community> communities) {
        this.communities = communities;
    }

    public FilteredExecutableResponse where(Condition condition) {
        return new FilteredExecutableResponse();
    }

    public List<Community> execute() {
        return communities;
    }
}
