package com.kiselev.matchmaker.search.service.target;

import com.google.common.collect.Lists;
import com.kiselev.matchmaker.search.service.Search;
import com.kiselev.matchmaker.search.service.concept.GroupSearchConcept;
import com.kiselev.matchmaker.search.service.concept.PostSearchConcept;
import com.kiselev.matchmaker.search.service.concept.UserSearchConcept;
import com.kiselev.matchmaker.search.service.target.general.GeneralGroupSearch;
import com.kiselev.matchmaker.search.service.target.general.GeneralPostSearch;
import com.kiselev.matchmaker.search.service.target.general.GeneralUserSearch;
import lombok.Setter;

import java.util.List;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
@Setter
public class FromSearch implements Search {

    private UserSearchConcept userSearch;

    private PostSearchConcept postSearch;

    private GroupSearchConcept groupSearch;

    public GeneralUserSearch fromUser(String userId) {
        return userSearch.from(Lists.newArrayList(userId));
    }

    public GeneralUserSearch fromUsers(String... userIds) {
        return userSearch.from(Lists.newArrayList(userIds));
    }

    public GeneralUserSearch fromUsers(List<String> userIds) {
        return userSearch.from(Lists.newArrayList(userIds));
    }

    public GeneralPostSearch fromPost(String postId) {
        return postSearch.from(Lists.newArrayList(postId));
    }

    public GeneralPostSearch fromPosts(String... postIds) {
        return postSearch.from(Lists.newArrayList(postIds));
    }

    public GeneralPostSearch fromPosts(List<String> postIds) {
        return postSearch.from(Lists.newArrayList(postIds));
    }

    public GeneralGroupSearch fromGroup(String groupId) {
        return groupSearch.from(Lists.newArrayList(groupId));
    }

    public GeneralGroupSearch fromGroups(String... groupIds) {
        return groupSearch.from(Lists.newArrayList(groupIds));
    }

    public GeneralGroupSearch fromGroups(List<String> groupIds) {
        return groupSearch.from(Lists.newArrayList(groupIds));
    }
}
