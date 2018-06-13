package com.kiselev.matchmaker.search.service.target.implementation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.kiselev.matchmaker.api.SocialNetworkAPI;
import com.kiselev.matchmaker.api.model.entity.Group;
import com.kiselev.matchmaker.api.model.entity.Post;
import com.kiselev.matchmaker.api.model.entity.User;
import com.kiselev.matchmaker.search.condition.SearchCondition;
import com.kiselev.matchmaker.search.condition.applier.ConditionApplier;
import com.kiselev.matchmaker.search.service.concept.GroupSearchConcept;
import com.kiselev.matchmaker.search.service.concept.PostSearchConcept;
import com.kiselev.matchmaker.search.service.concept.UserSearchConcept;
import com.kiselev.matchmaker.search.service.contract.GroupSearchContract;
import com.kiselev.matchmaker.search.service.target.factory.SearchFactory;
import com.kiselev.matchmaker.search.service.target.general.GeneralGroupSearch;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class GroupSearch implements GeneralGroupSearch {

    @Autowired
    private SocialNetworkAPI socialNetworkAPI;

    @Autowired
    private ConditionApplier<Group> groupConditionApplier;

    @Autowired
    private SearchFactory searchFactory;

    @JsonProperty
    private List<Group> groups;

    @Override
    public GroupSearchContract with() {
        // Not supported for posts
        return this;
    }

    @Override
    public UserSearchConcept subscribers() {
        List<User> subscribers = groups.stream()
                .map(Group::getId)
                .map(socialNetworkAPI::getSubscribersByGroupId)
                .flatMap(List::stream)
                .collect(Collectors.toList());
        return searchFactory.userSearch().fromEntities(subscribers);
    }

    @Override
    public PostSearchConcept posts() {
        List<Post> posts = groups.stream()
                .map(group -> "-" + group.getId()) // not documented point
                .map(socialNetworkAPI::getPostsByGroupId)
                .flatMap(List::stream)
                .collect(Collectors.toList());
        return searchFactory.postSearch().fromEntities(posts);
    }

    @Override
    public GeneralGroupSearch fromEntities(List<Group> groups) {
        this.groups = Lists.newArrayList(Sets.newLinkedHashSet(groups));
        return this;
    }

    @Override
    public GeneralGroupSearch from(List<String> groupIds) {
        fromEntities(socialNetworkAPI.getGroupsByGroupsIds(groupIds));
        return this;
    }

    @Override
    public GroupSearchConcept where(SearchCondition condition) {
        return fromEntities(groupConditionApplier.apply(groups, condition));
    }

    @Override
    public GroupSearchContract then() {
        return this;
    }

    @Override
    public List<Group> perform() {
        return Lists.newArrayList(Sets.newLinkedHashSet(groups));
    }
}
