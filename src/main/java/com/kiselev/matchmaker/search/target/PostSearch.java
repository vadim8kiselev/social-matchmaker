package com.kiselev.matchmaker.search.target;

import com.kiselev.matchmaker.search.response.NonFilteredExecutableResponse;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
public class PostSearch implements TargetSearch {

    private String postId;

    public NonFilteredExecutableResponse likers() {
        return new NonFilteredExecutableResponse();
    }

    public NonFilteredExecutableResponse sharers() {
        return new NonFilteredExecutableResponse();
    }

    @Override
    public PostSearch start(String postId) {
        this.postId = postId;
        return this;
    }
}
