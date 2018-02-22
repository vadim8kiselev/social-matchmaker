package com.kiselev.matchmaker.search.service.target;

import com.google.common.collect.Lists;
import com.kiselev.matchmaker.search.service.Search;
import com.kiselev.matchmaker.search.service.concept.GroupSearchConcept;
import com.kiselev.matchmaker.search.service.concept.PostSearchConcept;
import com.kiselev.matchmaker.search.service.concept.UserSearchConcept;
import com.kiselev.matchmaker.search.service.target.general.GeneralGroupSearch;
import com.kiselev.matchmaker.search.service.target.general.GeneralPostSearch;
import com.kiselev.matchmaker.search.service.target.general.GeneralUserSearch;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
public class FromSearch implements Search {

    @Autowired
    private UserSearchConcept userSearch;

    @Autowired
    private PostSearchConcept postSearch;

    @Autowired
    private GroupSearchConcept groupSearch;

    @Override
    public GeneralUserSearch fromUser(String userId) {
        return userSearch.from(Lists.newArrayList(userId));
    }

    @Override
    public GeneralUserSearch fromUsers(List<String> userIds) {
        return userSearch.from(Lists.newArrayList(userIds));
    }

    @Override
    public GeneralPostSearch fromPost(String postId) {
        return postSearch.from(Lists.newArrayList(postId));
    }

    @Override
    public GeneralPostSearch fromPosts(List<String> postIds) {
        return postSearch.from(Lists.newArrayList(postIds));
    }

    @Override
    public GeneralGroupSearch fromGroup(String groupId) {
        return groupSearch.from(Lists.newArrayList(groupId));
    }

    @Override
    public GeneralGroupSearch fromGroups(List<String> groupIds) {
        return groupSearch.from(Lists.newArrayList(groupIds));
    }
}
