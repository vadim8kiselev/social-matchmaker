package com.kiselev.matchmaker.api.implementation.facebook;

import com.kiselev.matchmaker.api.SocialNetworkAPI;
import com.kiselev.matchmaker.api.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
public class FacebookAPI implements SocialNetworkAPI {

    @Override
    public List<User> getFriendsByUserId(Integer userId) {
        return new ArrayList<>();
    }

    @Override
    public String getCurrentUserId() {
        return "";
    }
}
