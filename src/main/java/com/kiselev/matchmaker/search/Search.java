package com.kiselev.matchmaker.search;

import com.kiselev.matchmaker.search.service.implementation.GroupSearch;
import com.kiselev.matchmaker.search.service.implementation.PostSearch;
import com.kiselev.matchmaker.search.service.implementation.UserSearch;

import java.util.List;

/**
 * @author: Vadim Kiselev
 * @date: 12.02.2018
 */
public interface Search {

    UserSearch fromUser(String userId);

    UserSearch fromUsers(List<String> userIds);

    PostSearch fromPost(String postId);

    PostSearch fromPosts(List<String> postIds);

    GroupSearch fromGroup(String groupId);

    GroupSearch fromGroups(List<String> groupIds);
}
