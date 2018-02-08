package com.kiselev.matchmaker.api;

import com.kiselev.matchmaker.api.model.User;

import java.util.List;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
public interface SocialNetworkAPI {

    List<User> getFriendsByUserId(Integer userId);

    String getCurrentUserId();
}
