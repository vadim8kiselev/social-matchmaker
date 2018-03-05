package com.kiselev.matchmaker.search.service.target.implementation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.kiselev.matchmaker.api.SocialNetworkAPI;
import com.kiselev.matchmaker.api.model.entity.Post;
import com.kiselev.matchmaker.api.model.entity.User;
import com.kiselev.matchmaker.search.condition.SearchCondition;
import com.kiselev.matchmaker.search.condition.applier.ConditionApplier;
import com.kiselev.matchmaker.search.service.concept.PostSearchConcept;
import com.kiselev.matchmaker.search.service.concept.UserSearchConcept;
import com.kiselev.matchmaker.search.service.contract.PostSearchContract;
import com.kiselev.matchmaker.search.service.target.factory.SearchFactory;
import com.kiselev.matchmaker.search.service.target.general.GeneralPostSearch;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class PostSearch implements GeneralPostSearch {

    @Autowired
    private SocialNetworkAPI socialNetworkAPI;

    @Autowired
    private ConditionApplier<Post> postConditionApplier;

    @Autowired
    private SearchFactory searchFactory;

    @JsonProperty
    private List<Post> posts;

    @Override
    public PostSearchContract with() {
        // Not supported for posts
        return this;
    }

    @Override
    public UserSearchConcept likes() {
        List<User> likes = posts.stream()
                .map(Post::getId)
                .map(socialNetworkAPI::getLikesByPostId)
                .flatMap(List::stream)
                .collect(Collectors.toList());
        return searchFactory.userSearch().fromEntities(likes);
    }

    @Override
    public UserSearchConcept shares() {
        List<User> shares = posts.stream()
                .map(Post::getId)
                .map(socialNetworkAPI::getSharesByPostId)
                .flatMap(List::stream)
                .collect(Collectors.toList());
        return searchFactory.userSearch().fromEntities(shares);
    }

    @Override
    public GeneralPostSearch fromEntities(List<Post> posts) {
        this.posts = Lists.newArrayList(Sets.newLinkedHashSet(posts));
        return this;
    }

    @Override
    public GeneralPostSearch from(List<String> postsIds) {
        fromEntities(socialNetworkAPI.getPostsByPostsIds(postsIds));
        return this;
    }

    @Override
    public PostSearchConcept where(SearchCondition condition) {
        return fromEntities(postConditionApplier.apply(posts, condition));
    }

    @Override
    public PostSearchContract then() {
        return this;
    }

    @Override
    public List<Post> perform() {
        return Lists.newArrayList(Sets.newLinkedHashSet(posts));
    }
}
