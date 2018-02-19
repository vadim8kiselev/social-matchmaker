package com.kiselev.matchmaker.api.network.vk.implementation;

import com.google.gson.Gson;
import com.kiselev.matchmaker.api.SocialNetworkAPI;
import com.kiselev.matchmaker.api.model.entity.Group;
import com.kiselev.matchmaker.api.model.entity.Post;
import com.kiselev.matchmaker.api.model.entity.User;
import com.kiselev.matchmaker.api.network.vk.annotation.Doc;
import com.kiselev.matchmaker.api.network.vk.implementation.internal.SocialNetworkAPIInternal;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
public class VKAPI implements SocialNetworkAPI {

    @Autowired
    private SocialNetworkAPIInternal api;

    @Value("${vk.user.id}")
    private Integer userId;

    @Value("${vk.user.token}")
    private String token;

    private VkApiClient vk;

    private UserActor user;

    @PostConstruct
    public void auth() {
        vk = new VkApiClient(HttpTransportClient.getInstance(), new Gson(), Integer.MAX_VALUE);
        user = new UserActor(userId, token);
        api.auth(vk, user);
    }

    /**
     * User related methods
     */
    @Override
    @Doc(method = "users.get", maxCount = "1000")
    public List<User> getUsersByUsersIds(List<String> usersIds) {
        return api.getUsersByUsersIds(usersIds);
    }

    @Override
    @Doc(method = "friends.get", maxCount = "5000")
    public List<User> getFriendsByUserId(String userId) {
        return api.getFriendsByUserId(userId);
    }

    @Override
    @Doc(method = "users.getFollowers", maxCount = "1000")
    public List<User> getFollowersByUserId(String userId) {
        return api.getFollowersByUserId(userId);
    }

    @Override
    @Doc(method = "users.getSubscriptions", maxCount = "200")
    public List<User> getSubscriptionsByUserId(String userId) {
        return api.getSubscriptionsByUserId(userId);
    }

    @Override
    @Doc(method = "wall.get", maxCount = "100")
    public List<Post> getPostsByUserId(String userId) {
        return api.getPostsByOwnerId(userId);
    }

    @Override
    @Doc(method = "groups.get", maxCount = "1000")
    public List<Group> getGroupsByUserId(String userId) {
        return api.getGroupsByUserId(userId);
    }

    /**
     * Post related methods
     */
    @Override
    @Doc(method = "wall.getById", maxCount = "100")
    public List<Post> getPostsByPostsIds(List<String> postsIds) {
        return api.getPostsByPostsIds(postsIds);
    }

    @Override
    @Doc(method = "likes.getList", maxCount = "1000")
    public List<User> getLikesByPostId(String postId) {
        return api.getLikesByPostId(postId);
    }

    @Override
    @Doc(method = "wall.getReposts", maxCount = "1000")
    public List<User> getSharesByPostId(String postId) {
        return api.getSharesByPostId(postId);
    }

    /**
     * Group related methods
     */
    @Override
    @Doc(method = "groups.getById", maxCount = "1000")
    public List<Group> getGroupsByGroupsIds(List<String> groupsIds) {
        return api.getGroupsByGroupsIds(groupsIds);
    }

    @Override
    @Doc(method = "groups.getMembers", maxCount = "1000")
    public List<User> getSubscribersByGroupId(String groupId) {
        return api.getSubscribersByGroupId(groupId);
    }

    @Override
    @Doc(method = "wall.get", maxCount = "100")
    public List<Post> getPostsByGroupId(String groupId) {
        return api.getPostsByOwnerId(groupId);
    }
}
