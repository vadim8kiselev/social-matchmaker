package com.kiselev.matchmaker.api.network.facebook.implementation;

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
    public List<User> getFriendsByUserId(String userId) {
        throw new UnsupportedOperationException("Facebook API is not implemented yet.");
    }

    @Override
    public List<User> getFollowersByUserId(String userId) {
        throw new UnsupportedOperationException("Facebook API is not implemented yet.");
    }

    @Override
    public List<User> getSubscriptionsByUserId(String userId) {
        throw new UnsupportedOperationException("Facebook API is not implemented yet.");
    }

    @Override
    public List<Post> getPostsByUserId(String userId) {
        throw new UnsupportedOperationException("Facebook API is not implemented yet.");
    }

    @Override
    public List<Group> getGroupsByUserId(String userId) {
        throw new UnsupportedOperationException("Facebook API is not implemented yet.");
    }

    @Override
    public List<User> getLikesByPostId(String ownerId, String postId) {
        throw new UnsupportedOperationException("Facebook API is not implemented yet.");
    }

    @Override
    public List<User> getSharesByPostId(String ownerId, String postId) {
        throw new UnsupportedOperationException("Facebook API is not implemented yet.");
    }

    @Override
    public List<User> getSubscribersByGroupId(String groupId) {
        throw new UnsupportedOperationException("Facebook API is not implemented yet.");
    }

    @Override
    public List<Post> getPostsByGroupId(String groupId) {
        throw new UnsupportedOperationException("Facebook API is not implemented yet.");
    }
}
