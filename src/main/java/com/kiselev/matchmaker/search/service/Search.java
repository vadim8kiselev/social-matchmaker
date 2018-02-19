package com.kiselev.matchmaker.search.service;

import com.kiselev.matchmaker.search.service.target.general.GeneralGroupSearch;
import com.kiselev.matchmaker.search.service.target.general.GeneralPostSearch;
import com.kiselev.matchmaker.search.service.target.general.GeneralUserSearch;

import java.util.List;

public interface Search {

    GeneralUserSearch fromUser(String userId);

    GeneralUserSearch fromUsers(String... userIds);

    GeneralUserSearch fromUsers(List<String> userIds);

    GeneralPostSearch fromPost(String postId);

    GeneralPostSearch fromPosts(String... postIds);

    GeneralPostSearch fromPosts(List<String> postIds);

    GeneralGroupSearch fromGroup(String groupId);

    GeneralGroupSearch fromGroups(String... groupIds);

    GeneralGroupSearch fromGroups(List<String> groupIds);
}
