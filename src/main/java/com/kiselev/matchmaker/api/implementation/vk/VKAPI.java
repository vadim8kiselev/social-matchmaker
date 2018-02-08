package com.kiselev.matchmaker.api.implementation.vk;

import com.google.gson.Gson;
import com.kiselev.matchmaker.api.EntityConverter;
import com.kiselev.matchmaker.api.SocialNetworkAPI;
import com.kiselev.matchmaker.api.model.User;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.friends.UserXtrLists;
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
    private EntityConverter<UserXtrLists> converter;

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
    public List<User> getFriendsByUserId(Integer userId) {
        try {
            return vk.friends()
                    .get(user, UserField.values())
                    .userId(userId)
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
    public String getCurrentUserId() {
        return userId.toString();
    }
}
