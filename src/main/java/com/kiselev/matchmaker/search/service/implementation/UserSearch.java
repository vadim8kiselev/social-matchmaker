package com.kiselev.matchmaker.search.service.implementation;

import com.google.common.collect.Lists;
import com.kiselev.matchmaker.api.SocialNetworkAPI;
import com.kiselev.matchmaker.api.model.Group;
import com.kiselev.matchmaker.api.model.Post;
import com.kiselev.matchmaker.api.model.User;
import com.kiselev.matchmaker.search.operation.implementation.GroupOperation;
import com.kiselev.matchmaker.search.operation.implementation.PostOperation;
import com.kiselev.matchmaker.search.operation.implementation.UserOperation;
import com.kiselev.matchmaker.search.service.PerformableSearch;
import com.kiselev.matchmaker.search.service.StatefulSearch;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
public class UserSearch implements StatefulSearch, PerformableSearch<User> {

    @Autowired
    private SocialNetworkAPI socialNetworkAPI;

    @Autowired
    private UserOperation userOperation;

    @Autowired
    private PostOperation postOperation;

    @Autowired
    private GroupOperation groupOperation;

    private List<String> userIds;

    public UserOperation friends() {
        List<User> friends = userIds.stream()
                .map(socialNetworkAPI::getFriendsByUserId)
                .flatMap(List::stream)
                .collect(Collectors.toList());
        return userOperation.from(friends);
    }

    public UserOperation followers() {
        List<User> followers = userIds.stream()
                .map(socialNetworkAPI::getFollowersByUserId)
                .flatMap(List::stream)
                .collect(Collectors.toList());
        return userOperation.from(followers);
    }

    public UserOperation subscriptions() {
        List<User> subscriptions = userIds.stream()
                .map(socialNetworkAPI::getSubscriptionsByUserId)
                .flatMap(List::stream)
                .collect(Collectors.toList());
        return userOperation.from(subscriptions);
    }

    public PostOperation posts() {
        List<Post> posts = userIds.stream()
                .map(socialNetworkAPI::getPostsByUserId)
                .flatMap(List::stream)
                .collect(Collectors.toList());
        return postOperation.from(posts);
    }

    public GroupOperation groups() {
        List<Group> groups = userIds.stream()
                .map(socialNetworkAPI::getGroupsByUserId)
                .flatMap(List::stream)
                .collect(Collectors.toList());
        return groupOperation.from(groups);
    }

    @Override
    public UserSearch from(String... userIds) {
        this.userIds = Lists.newArrayList(userIds);
        return this;
    }

    @Override
    public UserSearch from(List<String> userIds) {
        this.userIds = Lists.newArrayList(userIds);
        return this;
    }

    @Override
    public List<User> perform() {
        return socialNetworkAPI.getUsersByUsersIds(userIds);
    }
}
