package com.kiselev.matchmaker.api.network.vk.implementation.internal;

import com.kiselev.matchmaker.api.SocialNetworkAPI;
import com.kiselev.matchmaker.api.model.entity.Post;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;

import java.util.List;

public interface VKAPIInternal extends SocialNetworkAPI {

    void auth(VkApiClient vk, UserActor user);

    List<Post> getPostsByOwnerId(String ownerId);
}
