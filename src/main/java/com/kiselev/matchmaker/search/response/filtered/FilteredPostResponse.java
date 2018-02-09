package com.kiselev.matchmaker.search.response.filtered;

import com.google.common.collect.Lists;
import com.kiselev.matchmaker.api.model.Post;
import com.kiselev.matchmaker.search.response.ExecutableResponse;
import com.kiselev.matchmaker.search.response.NonFilteredExecutableResponse;

import java.util.List;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
public class FilteredPostResponse implements ExecutableResponse<Post> {

    private List<Post> data;

    public NonFilteredExecutableResponse then() {
        return new NonFilteredExecutableResponse();
    }

    public List<Post> execute() {
        return Lists.newArrayList();
    }
}
