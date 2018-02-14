package com.kiselev.matchmaker.search.service.implementation;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kiselev.matchmaker.api.SocialNetworkAPI;
import com.kiselev.matchmaker.api.model.Post;
import com.kiselev.matchmaker.api.model.User;
import com.kiselev.matchmaker.search.operation.implementation.UserOperation;
import com.kiselev.matchmaker.search.service.PerformableSearch;
import com.kiselev.matchmaker.search.service.StatefulSearch;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
public class PostSearch implements StatefulSearch, PerformableSearch<Post> {

    @Autowired
    private SocialNetworkAPI socialNetworkAPI;

    @Autowired
    private UserOperation userOperation;

    //   Map: owner_id, posts_ids
    private Map<String, List<String>> postsIds;

    public UserOperation likes() {
        List<User> likes = postsIds.entrySet().stream()
                .map(entry -> entry.getValue().stream()
                        .map(postId -> socialNetworkAPI.getLikesByPostId(entry.getKey(), postId))
                        .flatMap(List::stream)
                        .collect(Collectors.toList()))
                .flatMap(List::stream)
                .collect(Collectors.toList());
        return userOperation.from(likes);
    }

    public UserOperation shares() {
        List<User> shares = postsIds.entrySet().stream()
                .map(entry -> entry.getValue().stream()
                        .map(postId -> socialNetworkAPI.getSharesByPostId(entry.getKey(), postId))
                        .flatMap(List::stream)
                        .collect(Collectors.toList()))
                .flatMap(List::stream)
                .collect(Collectors.toList());
        return userOperation.from(shares);
    }

    @Override
    public PostSearch from(String... postsIds) {
        this.postsIds = internalFrom(Lists.newArrayList(postsIds));
        return this;
    }

    @Override
    public PostSearch from(List<String> postsIds) {
        this.postsIds = internalFrom(Lists.newArrayList(postsIds));
        return this;
    }

    private Map<String, List<String>> internalFrom(List<String> postsIds) {
        Map<String, List<String>> composedPostsIds = Maps.newHashMap();
        for (String id : postsIds) {
            String ownerId = id.split("_")[0];
            String postId = id.split("_")[1];

            if (composedPostsIds.containsKey(ownerId)) {
                composedPostsIds.get(ownerId).add(postId);
            } else {
                composedPostsIds.put(ownerId, Lists.newArrayList(postId));
            }
        }
        return composedPostsIds;
    }

    @Override
    public List<Post> perform() {
        return socialNetworkAPI.getPostsByPostsIds(postsIds);
    }
}
