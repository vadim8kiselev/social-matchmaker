package com.kiselev.matchmaker.api;

import com.kiselev.matchmaker.api.model.entity.Group;
import com.kiselev.matchmaker.api.model.entity.Post;
import com.kiselev.matchmaker.api.model.entity.User;

import java.util.List;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
public interface SocialNetworkAPI {

    /**
     * User related methods
     */
    List<User> getUsersByUsersIds(List<String> usersIds);

    List<User> getFriendsByUserId(String userId);

    List<User> getFollowersByUserId(String userId);

    List<User> getSubscriptionsByUserId(String userId);

    List<Post> getPostsByUserId(String userId);

    List<Group> getGroupsByUserId(String userId);

    /**
     * Post related methods
     */
    List<Post> getPostsByPostsIds(List<String> postsIds);

    List<User> getLikesByPostId(String postId);

    List<User> getSharesByPostId(String postId);

    /**
     * Group related methods
     */
    List<Group> getGroupsByGroupsIds(List<String> groupsIds);

    List<User> getSubscribersByGroupId(String groupId);

    List<Post> getPostsByGroupId(String groupId);
}
