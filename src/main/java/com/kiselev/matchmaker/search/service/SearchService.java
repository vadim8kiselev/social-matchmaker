package com.kiselev.matchmaker.search.service;

import com.kiselev.matchmaker.search.Search;
import com.kiselev.matchmaker.search.service.implementation.GroupSearch;
import com.kiselev.matchmaker.search.service.implementation.PostSearch;
import com.kiselev.matchmaker.search.service.implementation.UserSearch;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
public class SearchService implements Search {

    @Autowired
    private UserSearch userSearch;

    @Autowired
    private PostSearch postSearch;

    @Autowired
    private GroupSearch groupSearch;

    public UserSearch fromUser(String userId) {
        return userSearch.from(userId);
    }

    public UserSearch fromUsers(List<String> userIds) {
        return userSearch.from(userIds);
    }

    public PostSearch fromPost(String postId) {
        return postSearch.from(postId);
    }

    public PostSearch fromPosts(List<String> postIds) {
        return postSearch.from(postIds);
    }

    public GroupSearch fromGroup(String groupId) {
        return groupSearch.from(groupId);
    }

    public GroupSearch fromGroups(List<String> groupIds) {
        return groupSearch.from(groupIds);
    }
}
