package com.kiselev.matchmaker.search;

import com.kiselev.matchmaker.api.SocialNetworkAPI;
import com.kiselev.matchmaker.search.target.CommunitySearch;
import com.kiselev.matchmaker.search.target.PostSearch;
import com.kiselev.matchmaker.search.target.UserSearch;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
public class Search {

    @Autowired
    private SocialNetworkAPI socialNetworkAPI;

    @Autowired
    private UserSearch userSearch;

    @Autowired
    private PostSearch postSearch;

    @Autowired
    private CommunitySearch communitySearch;

    public UserSearch fromMe() {
        return userSearch.start(socialNetworkAPI.getCurrentUserId());
    }

    public UserSearch fromUser(String userId) {
        return userSearch.start(userId);
    }

    public PostSearch fromPost(String postId) {
        return postSearch.start(postId); // TODO: check this
    }

    public CommunitySearch fromCommunity(String communityId) {
        return communitySearch.start(communityId);
    }
}
