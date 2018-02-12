package com.kiselev.matchmaker.search.service.implementation;

import com.google.common.collect.Lists;
import com.kiselev.matchmaker.api.SocialNetworkAPI;
import com.kiselev.matchmaker.api.model.Post;
import com.kiselev.matchmaker.api.model.User;
import com.kiselev.matchmaker.search.operation.implementation.PostOperation;
import com.kiselev.matchmaker.search.operation.implementation.UserOperation;
import com.kiselev.matchmaker.search.service.StatefulSearch;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
public class GroupSearch implements StatefulSearch {

    @Autowired
    private SocialNetworkAPI socialNetworkAPI;

    @Autowired
    private UserOperation userOperation;

    @Autowired
    private PostOperation postOperation;

    private List<String> groupIds;

    public UserOperation subscribers() {
        List<User> subscribers = groupIds.stream()
                .map(socialNetworkAPI::getSubscribersByGroupId)
                .flatMap(List::stream)
                .collect(Collectors.toList());
        return userOperation.from(subscribers);
    }

    public PostOperation posts() {
        List<Post> posts = groupIds.stream()
                .map(socialNetworkAPI::getPostsByGroupId)
                .flatMap(List::stream)
                .collect(Collectors.toList());
        return postOperation.from(posts);
    }

    @Override
    public GroupSearch from(String... groupIds) {
        this.groupIds = Lists.newArrayList(groupIds);
        return this;
    }

    @Override
    public GroupSearch from(List<String> groupIds) {
        this.groupIds = Lists.newArrayList(groupIds);
        return this;
    }
}
