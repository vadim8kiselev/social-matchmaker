package com.kiselev.matchmaker.api.network.vk.implementation;

import com.google.gson.Gson;
import com.kiselev.matchmaker.api.SocialNetworkAPI;
import com.kiselev.matchmaker.api.model.entity.Group;
import com.kiselev.matchmaker.api.model.entity.Post;
import com.kiselev.matchmaker.api.model.entity.User;
import com.kiselev.matchmaker.api.network.vk.annotation.Doc;
import com.kiselev.matchmaker.api.network.vk.configuration.VKAPIConfiguration;
import com.kiselev.matchmaker.api.network.vk.implementation.internal.VKAPIInternal;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.UserAuthResponse;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
public class VKAPI implements SocialNetworkAPI {

    @Autowired
    private VKAPIInternal api;

    @Autowired
    private VKAPIConfiguration configuration;

    @PostConstruct
    public void auth() {
        VkApiClient vk = new VkApiClient(HttpTransportClient.getInstance(), new Gson(), Integer.MAX_VALUE);
        UserActor user;

        try {
            UserAuthResponse authResponse = vk.oauth()
                    .userAuthorizationCodeFlow(configuration.getClientId(),
                            configuration.getClientSecret(),
                            configuration.getRedirectUri(),
                            configuration.getSecretCode())
                    .execute();

            Integer userId = authResponse.getUserId();
            String accessToken = authResponse.getAccessToken();
            user = new UserActor(userId, accessToken);

            api.auth(vk, user);
        } catch (ApiException | ClientException exception) {
            exception.printStackTrace();
            System.exit(1);
        }
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
