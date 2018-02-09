package com.kiselev.matchmaker.search.target;

import com.kiselev.matchmaker.api.SocialNetworkAPI;
import com.kiselev.matchmaker.api.model.Community;
import com.kiselev.matchmaker.api.model.Post;
import com.kiselev.matchmaker.api.model.User;
import com.kiselev.matchmaker.search.response.nonfiltered.NonFilteredCommunityResponse;
import com.kiselev.matchmaker.search.response.nonfiltered.NonFilteredPostResponse;
import com.kiselev.matchmaker.search.response.nonfiltered.NonFilteredUserResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
public class UserSearch implements TargetSearch {

    @Autowired
    private SocialNetworkAPI socialNetworkAPI;

    private String userId; // null

    public NonFilteredUserResponse friends() {
        List<User> friends = socialNetworkAPI.getFriendsByUserId(userId);
        return new NonFilteredUserResponse(friends);
    }

    public NonFilteredUserResponse followers() {
        List<User> followers = socialNetworkAPI.getFollowersByUserId(userId);
        return new NonFilteredUserResponse(followers);
    }

    public NonFilteredUserResponse subscriptions() {
        List<User> subscriptions = socialNetworkAPI.getSubscriptionsByUserId(userId);
        return new NonFilteredUserResponse(subscriptions);
    }

    public NonFilteredPostResponse posts() {
        List<Post> posts = socialNetworkAPI.getPostsByUserId(userId);
        return new NonFilteredPostResponse(posts);
    }

    public NonFilteredCommunityResponse communities() {
        List<Community> communities = socialNetworkAPI.getCommunitiesByUserId(userId);
        return new NonFilteredCommunityResponse(communities);
    }

    @Override
    public UserSearch start(String userId) {
        this.userId = userId;
        return this;
    }
}
