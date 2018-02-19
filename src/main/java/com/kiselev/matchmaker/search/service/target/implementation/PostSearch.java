package com.kiselev.matchmaker.search.service.target.implementation;

import com.google.common.collect.Lists;
import com.kiselev.matchmaker.api.SocialNetworkAPI;
import com.kiselev.matchmaker.api.model.entity.Post;
import com.kiselev.matchmaker.api.model.entity.User;
import com.kiselev.matchmaker.search.condition.SearchCondition;
import com.kiselev.matchmaker.search.condition.applier.ConditionApplier;
import com.kiselev.matchmaker.search.service.concept.PostSearchConcept;
import com.kiselev.matchmaker.search.service.concept.UserSearchConcept;
import com.kiselev.matchmaker.search.service.contract.PostSearchContract;
import com.kiselev.matchmaker.search.service.target.general.GeneralPostSearch;
import com.kiselev.matchmaker.search.service.target.general.passive.PassiveGeneralPostSearch;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
@Setter
public class PostSearch implements PassiveGeneralPostSearch {

    private SocialNetworkAPI socialNetworkAPI;

    private ConditionApplier<Post> postConditionApplier;

    private UserSearchConcept userSearch;

    private List<Post> posts;

    @Override
    public UserSearchConcept likes() {
        List<User> likes = posts.stream()
                .map(post -> post.getOwnerId() + "_" + post.getId()) // TODO: Remove VK related functionality
                .map(socialNetworkAPI::getLikesByPostId)
                .flatMap(List::stream)
                .collect(Collectors.toList());
        return userSearch.fromEntities(likes);
    }

    @Override
    public UserSearchConcept shares() {
        List<User> shares = posts.stream()
                .map(post -> post.getOwnerId() + "_" + post.getId()) // TODO: VK related functionality
                .map(socialNetworkAPI::getSharesByPostId)
                .flatMap(List::stream)
                .collect(Collectors.toList());
        return userSearch.fromEntities(shares);
    }

    @Override
    public GeneralPostSearch fromEntities(List<Post> posts) {
        this.posts = Lists.newArrayList(posts);
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
        return posts;
    }
}
