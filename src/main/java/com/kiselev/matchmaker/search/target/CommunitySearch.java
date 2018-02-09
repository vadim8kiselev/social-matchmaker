package com.kiselev.matchmaker.search.target;

import com.kiselev.matchmaker.search.response.NonFilteredExecutableResponse;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
public class CommunitySearch implements TargetSearch {

    private String communityId;

    public NonFilteredExecutableResponse subscribers() {
        return new NonFilteredExecutableResponse();
    }

    @Override
    public CommunitySearch start(String communityId) {
        this.communityId = communityId;
        return this;
    }
}
