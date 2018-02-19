package com.kiselev.matchmaker.search.service.target.implementation;

import com.google.common.collect.Lists;
import com.kiselev.matchmaker.api.SocialNetworkAPI;
import com.kiselev.matchmaker.api.model.entity.Group;
import com.kiselev.matchmaker.api.model.entity.Post;
import com.kiselev.matchmaker.api.model.entity.User;
import com.kiselev.matchmaker.search.condition.SearchCondition;
import com.kiselev.matchmaker.search.condition.applier.ConditionApplier;
import com.kiselev.matchmaker.search.service.concept.GroupSearchConcept;
import com.kiselev.matchmaker.search.service.concept.PostSearchConcept;
import com.kiselev.matchmaker.search.service.concept.UserSearchConcept;
import com.kiselev.matchmaker.search.service.contract.UserSearchContract;
import com.kiselev.matchmaker.search.service.target.general.GeneralUserSearch;
import com.kiselev.matchmaker.search.service.target.general.passive.PassiveGeneralUserSearch;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
@Setter
public class UserSearch implements PassiveGeneralUserSearch {

    private SocialNetworkAPI socialNetworkAPI;

    private ConditionApplier<User> userConditionApplier;

    private PostSearchConcept postSearch;

    private GroupSearchConcept groupSearch;

    private List<User> users;

    @Override
    public UserSearchConcept friends() {
        List<User> friends = users.stream()
                .map(User::getId)
                .map(socialNetworkAPI::getFriendsByUserId)
                .flatMap(List::stream)
                .collect(Collectors.toList());
        return fromEntities(friends);
    }

    @Override
    public UserSearchConcept followers() {
        List<User> followers = users.stream()
                .map(User::getId)
                .map(socialNetworkAPI::getFollowersByUserId)
                .flatMap(List::stream)
                .collect(Collectors.toList());
        return fromEntities(followers);
    }

    @Override
    public UserSearchConcept subscriptions() {
        List<User> subscriptions = users.stream()
                .map(User::getId)
                .map(socialNetworkAPI::getSubscriptionsByUserId)
                .flatMap(List::stream)
                .collect(Collectors.toList());
        return fromEntities(subscriptions);
    }

    @Override
    public PostSearchConcept posts() {
        List<Post> posts = users.stream()
                .map(User::getId)
                .map(socialNetworkAPI::getPostsByUserId)
                .flatMap(List::stream)
                .collect(Collectors.toList());
        return postSearch.fromEntities(posts);
    }

    @Override
    public GroupSearchConcept groups() {
        List<Group> groups = users.stream()
                .map(User::getId)
                .map(socialNetworkAPI::getGroupsByUserId)
                .flatMap(List::stream)
                .collect(Collectors.toList());
        return groupSearch.fromEntities(groups);
    }

    @Override
    public GeneralUserSearch fromEntities(List<User> users) {
        this.users = Lists.newArrayList(users);
        return this;
    }

    @Override
    public GeneralUserSearch from(List<String> userIds) {
        fromEntities(socialNetworkAPI.getUsersByUsersIds(userIds));
        return this;
    }

    @Override
    public UserSearchConcept where(SearchCondition condition) {
        return fromEntities(userConditionApplier.apply(users, condition));
    }

    @Override
    public UserSearchContract then() {
        return this;
    }

    @Override
    public List<User> perform() {
        return users;
    }
}
