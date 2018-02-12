package com.kiselev.matchmaker.api;

import com.kiselev.matchmaker.api.model.Group;
import com.kiselev.matchmaker.api.model.Post;
import com.kiselev.matchmaker.api.model.User;

import java.util.List;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
public interface SocialNetworkAPI {

    void auth();

    String getCurrentUserId();

    // User
    List<User> getFriendsByUserId(String userId);

    List<User> getFollowersByUserId(String userId);

    List<User> getSubscriptionsByUserId(String userId);

    List<Post> getPostsByUserId(String userId);

    List<Group> getGroupsByUserId(String userId);

    // Post
    List<User> getLikersByPostId(String ownerId, String postId);

    List<User> getSharersByPostId(String ownerId, String postId);

    // Group
    List<User> getSubscribersByGroupId(String groupId);

    List<Post> getPostsByGroupId(String groupId);
}
