package com.kiselev.matchmaker.search.service.implementation;

import com.google.common.collect.Lists;
import com.kiselev.matchmaker.api.SocialNetworkAPI;
import com.kiselev.matchmaker.api.model.User;
import com.kiselev.matchmaker.search.operation.implementation.UserOperation;
import com.kiselev.matchmaker.search.service.StatefulSearch;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
public class PostSearch implements StatefulSearch {

    @Autowired
    private SocialNetworkAPI socialNetworkAPI;

    @Autowired
    private UserOperation userOperation;

    //   Map: owner_id, post_id
    private Map<String, String> postIds;

    public UserOperation likes() {
        List<User> likes = postIds.entrySet().stream()
                .map(entry -> socialNetworkAPI.getLikesByPostId(entry.getKey(), entry.getValue()))
                .flatMap(List::stream)
                .collect(Collectors.toList());
        return userOperation.from(likes);
    }

    public UserOperation shares() {
        List<User> shares = postIds.entrySet().stream()
                .map(entry -> socialNetworkAPI.getSharesByPostId(entry.getKey(), entry.getValue()))
                .flatMap(List::stream)
                .collect(Collectors.toList());
        return userOperation.from(shares);
    }

    @Override
    public PostSearch from(String... postIds) {
        this.postIds = Lists.newArrayList(postIds).stream()
                .collect(Collectors.toMap(id -> id.split("_")[0],
                                          id -> id.split("_")[1]));
        return this;
    }

    @Override
    public PostSearch from(List<String> postIds) {
        this.postIds = Lists.newArrayList(postIds).stream()
                .collect(Collectors.toMap(id -> id.split("_")[0],
                                          id -> id.split("_")[1]));
        return this;
    }
}
