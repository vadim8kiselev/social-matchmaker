package com.kiselev.matchmaker.api.implementation.vk;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.kiselev.matchmaker.api.EntityConverter;
import com.kiselev.matchmaker.api.SocialNetworkAPI;
import com.kiselev.matchmaker.api.model.Group;
import com.kiselev.matchmaker.api.model.Post;
import com.kiselev.matchmaker.api.model.User;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ApiUserDeletedException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.groups.GroupFull;
import com.vk.api.sdk.objects.users.UserFull;
import com.vk.api.sdk.objects.wall.WallpostFull;
import com.vk.api.sdk.queries.likes.LikesType;
import com.vk.api.sdk.queries.users.UserField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
public class VKAPI implements SocialNetworkAPI {

    @Autowired
    private EntityConverter<UserFull, WallpostFull, GroupFull> converter;

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
    }

    @Override
    public String getCurrentUserId() {
        return user.getId().toString();
    }

    // User
    @Override
    public List<User> getFriendsByUserId(String userId) {
        try {
            return vk.friends()
                    .get(user, UserField.values())
                    .userId(Integer.parseInt(userId))
                    .execute()
                    .getItems().stream()
                    .map(converter::convertUser)
                    .collect(Collectors.toList());
        } catch (ApiException | ClientException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getFollowersByUserId(String userId) {
        try {
            return vk.users()
                    .getFollowers(user, UserField.values())
                    .userId(Integer.parseInt(userId))
                    .execute()
                    .getItems().stream()
                    .map(converter::convertUser)
                    .collect(Collectors.toList());
        } catch (ApiException | ClientException e) {
            e.printStackTrace();
        }
        return Lists.newArrayList();
    }

    @Override
    public List<User> getSubscriptionsByUserId(String userId) {
        try {
            List<String> subscriptionsIds = vk.users()
                    .getSubscriptions(user)
                    .userId(Integer.parseInt(userId))
                    .execute()
                    .getUsers().getItems().stream()
                    .map(Object::toString)
                    .collect(Collectors.toList());

            return vk.users().get(user)
                    .userIds(subscriptionsIds)
                    .execute().stream()
                    .map(converter::convertUser)
                    .collect(Collectors.toList());

        } catch (ApiException | ClientException e) {
            e.printStackTrace();
        }
        return Lists.newArrayList();
    }

    @Override
    public List<Post> getPostsByUserId(String userId) {
        try {
            return vk.wall().get(user)
                    .ownerId(Integer.parseInt(userId))
                    .execute().getItems().stream()
                    .map(converter::convertPost)
                    .collect(Collectors.toList());
        } catch (ApiException | ClientException e) {
            e.printStackTrace();
        }
        return Lists.newArrayList();
    }

    @Override
    public List<Group> getGroupsByUserId(String userId) {
        try {
            List<String> groupsIds = vk.groups().get(user)
                    .userId(Integer.parseInt(userId))
                    .execute().getItems().stream()
                    .map(Object::toString)
                    .collect(Collectors.toList());

            return vk.groups().getById(user).groupIds(groupsIds)
                    .execute().stream()
                    .map(converter::convertGroup)
                    .collect(Collectors.toList());
        } catch (ApiException | ClientException e) {
            e.printStackTrace();
        }
        return Lists.newArrayList();
    }

    // Post
    @Override
    public List<User> getLikersByPostId(String ownerId, String postId) {
        try {
            List<String> likersIds = vk.likes().getList(user, LikesType.POST)
                    .ownerId(Integer.parseInt(ownerId))
                    .count(1000)
                    .itemId(Integer.parseInt(postId))
                    .execute().getItems().stream()
                    .map(Object::toString)
                    .collect(Collectors.toList());

            return vk.users().get(user).userIds(likersIds)
                    .execute().stream()
                    .map(converter::convertUser)
                    .collect(Collectors.toList());
        } catch (ApiException | ClientException e) {
            e.printStackTrace();
        }

        return Lists.newArrayList();
    }

    @Override
    public List<User> getSharersByPostId(String ownerId, String postId) {
        try {
            List<String> sharersIds = vk.wall().getReposts(user)
                    .ownerId(Integer.parseInt(ownerId))
                    .count(1000)
                    .postId(Integer.parseInt(postId))
                    .execute().getProfiles().stream()
                    .map(com.vk.api.sdk.objects.users.User::getId)
                    .map(Object::toString)
                    .collect(Collectors.toList());

            return vk.users().get(user)
                    .userIds(sharersIds)
                    .execute().stream()
                    .map(converter::convertUser)
                    .collect(Collectors.toList());

        } catch (ApiException | ClientException e) {
            e.printStackTrace();
        }

        return Lists.newArrayList();
    }

    // Group
    @Override
    public List<User> getSubscribersByGroupId(String groupId) {
        try {
            List<String> subscribers = vk.groups().getMembers(user)
                    .groupId(groupId)
                    .execute().getItems().stream()
                    .map(Object::toString)
                    .collect(Collectors.toList());

            return vk.users().get(user)
                    .userIds(subscribers)
                    .execute().stream()
                    .map(converter::convertUser)
                    .collect(Collectors.toList());
        } catch (ApiException | ClientException e) {
            e.printStackTrace();
        }

        return Lists.newArrayList();
    }

    @Override
    public List<Post> getPostsByGroupId(String groupId) {
        try {
            return vk.wall().get(user)
                    .ownerId(Integer.parseInt(groupId))
                    .count(100)
                    .execute().getItems().stream()
                    .map(converter::convertPost)
                    .collect(Collectors.toList());
        } catch (ApiException | ClientException e) {
            e.printStackTrace();
        }

        return Lists.newArrayList();
    }

    public void setConverter(EntityConverter<UserFull, WallpostFull, GroupFull> converter) {
        this.converter = converter;
    }
}
