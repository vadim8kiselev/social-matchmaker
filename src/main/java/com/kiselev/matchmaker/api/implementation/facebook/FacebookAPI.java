package com.kiselev.matchmaker.api.implementation.facebook;

import com.google.common.collect.Lists;
import com.kiselev.matchmaker.api.SocialNetworkAPI;
import com.kiselev.matchmaker.api.model.Community;
import com.kiselev.matchmaker.api.model.Post;
import com.kiselev.matchmaker.api.model.User;

import java.util.List;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
public class FacebookAPI implements SocialNetworkAPI {

    @Override
    public List<User> getFriendsByUserId(String userId) {
        return Lists.newArrayList();
    }

    @Override
    public List<User> getFollowersByUserId(String userId) {
        return Lists.newArrayList();
    }

    @Override
    public List<User> getSubscriptionsByUserId(String userId) {
        return Lists.newArrayList();
    }

    @Override
    public List<Post> getPostsByUserId(String userId) {
        return Lists.newArrayList();
    }

    @Override
    public List<Community> getCommunitiesByUserId(String userId) {
        return Lists.newArrayList();
    }

    @Override
    public String getCurrentUserId() {
        return "1";
    }
}
