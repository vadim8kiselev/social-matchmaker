package com.kiselev.matchmaker.api;

import com.kiselev.matchmaker.api.model.Community;
import com.kiselev.matchmaker.api.model.Post;
import com.kiselev.matchmaker.api.model.User;

import java.util.List;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
public interface SocialNetworkAPI {

    String getCurrentUserId();

    List<User> getFriendsByUserId(String userId);

    List<User> getFollowersByUserId(String userId);

    List<User> getSubscriptionsByUserId(String userId);

    List<Post> getPostsByUserId(String userId);

    List<Community> getCommunitiesByUserId(String userId);
}
