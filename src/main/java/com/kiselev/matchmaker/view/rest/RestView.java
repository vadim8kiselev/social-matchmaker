package com.kiselev.matchmaker.view.rest;

import com.kiselev.matchmaker.search.service.concept.GroupSearchConcept;
import com.kiselev.matchmaker.search.service.concept.PostSearchConcept;
import com.kiselev.matchmaker.search.service.concept.UserSearchConcept;
import com.kiselev.matchmaker.search.service.contract.GroupSearchContract;
import com.kiselev.matchmaker.search.service.contract.PostSearchContract;
import com.kiselev.matchmaker.search.service.contract.UserSearchContract;
import com.kiselev.matchmaker.search.service.target.general.GeneralGroupSearch;
import com.kiselev.matchmaker.search.service.target.general.GeneralPostSearch;
import com.kiselev.matchmaker.search.service.target.general.GeneralUserSearch;
import com.kiselev.matchmaker.view.rest.model.PerformResponse;
import com.kiselev.matchmaker.view.rest.model.Response;

import java.util.List;

public interface RestView {

    Response start();

    Response fromUser(String userId);

    Response fromUsers(List<String> usersIds);

    Response fromPost(String postId);

    Response fromPosts(List<String> postsIds);

    Response fromGroup(String groupId);

    Response fromGroups(List<String> groupsIds);

    Response friends(UserSearchContract userSearch);

    Response followers(UserSearchContract userSearch);

    Response subscriptions(UserSearchContract userSearch);

    Response posts(UserSearchContract userSearch);

    Response groups(UserSearchContract userSearch);

    Response likes(PostSearchContract postSearch);

    Response shares(PostSearchContract postSearch);

    Response subscribers(GroupSearchContract groupSearch);

    Response posts(GroupSearchContract groupSearch);

    Response where(UserSearchConcept userSearchConcept /* Condition condition */);

    Response where(PostSearchConcept postSearchConcept /* Condition condition */);

    Response where(GroupSearchConcept groupSearchConcept /* Condition condition */);

    Response then(UserSearchConcept userSearchConcept);

    Response then(PostSearchConcept postSearchConcept);

    Response then(GroupSearchConcept groupSearchConcept);

    PerformResponse perform(GeneralUserSearch userSearch, String type);

    PerformResponse perform(GeneralPostSearch groupSearch, String type);

    PerformResponse perform(GeneralGroupSearch groupSearch, String type);
}
