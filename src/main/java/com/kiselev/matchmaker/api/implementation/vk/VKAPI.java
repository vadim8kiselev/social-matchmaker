package com.kiselev.matchmaker.api.implementation.vk;

import com.google.gson.Gson;
import com.kiselev.matchmaker.api.EntityConverter;
import com.kiselev.matchmaker.api.SocialNetworkAPI;
import com.kiselev.matchmaker.api.model.Community;
import com.kiselev.matchmaker.api.model.Post;
import com.kiselev.matchmaker.api.model.User;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.groups.GroupFull;
import com.vk.api.sdk.objects.users.UserFull;
import com.vk.api.sdk.objects.wall.WallpostFull;
import com.vk.api.sdk.queries.users.UserField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
@Service
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
        vk = new VkApiClient(HttpTransportClient.getInstance(), new Gson(), 8);
        user = new UserActor(userId, token);
    }

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
        return null;
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
        return null;
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
        return null;
    }

    @Override
    public List<Community> getCommunitiesByUserId(String userId) {
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
        return null;
    }

    @Override
    public String getCurrentUserId() {
        return userId.toString();
    }
}
