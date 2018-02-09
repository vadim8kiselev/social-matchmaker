package com.kiselev.matchmaker.search.response.nonfiltered;

import com.kiselev.matchmaker.api.model.Post;
import com.kiselev.matchmaker.search.condition.Condition;
import com.kiselev.matchmaker.search.response.ExecutableResponse;
import com.kiselev.matchmaker.search.response.FilteredExecutableResponse;

import java.util.List;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
public class NonFilteredPostResponse implements ExecutableResponse<Post> {

    private List<Post> posts;

    public NonFilteredPostResponse(List<Post> posts) {
        this.posts = posts;
    }

    public FilteredExecutableResponse where(Condition condition) {
        return new FilteredExecutableResponse();
    }

    public List<Post> execute() {
        return posts;
    }
}
