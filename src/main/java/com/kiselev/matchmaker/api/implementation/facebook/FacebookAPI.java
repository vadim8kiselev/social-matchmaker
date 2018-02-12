package com.kiselev.matchmaker.api.implementation.facebook;

import com.google.common.collect.Lists;
import com.kiselev.matchmaker.api.SocialNetworkAPI;
import com.kiselev.matchmaker.api.model.Group;
import com.kiselev.matchmaker.api.model.Post;
import com.kiselev.matchmaker.api.model.User;

import java.util.List;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
public class FacebookAPI implements SocialNetworkAPI {

    @Override
    public void auth() {
        // do nothing
    }

    @Override
    public String getCurrentUserId() {
        return "1";
    }

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
    public List<Group> getGroupsByUserId(String userId) {
        return Lists.newArrayList();
    }

    @Override
    public List<User> getLikersByPostId(String ownerId, String postId) {
        return Lists.newArrayList();
    }

    @Override
    public List<User> getSharersByPostId(String ownerId, String postId) {
        return Lists.newArrayList();
    }

    @Override
    public List<User> getSubscribersByGroupId(String groupId) {
        return Lists.newArrayList();
    }

    @Override
    public List<Post> getPostsByGroupId(String groupId) {
        return Lists.newArrayList();
    }
}
