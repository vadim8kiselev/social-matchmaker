package com.kiselev.matchmaker.search;

import com.kiselev.matchmaker.api.SocialNetworkAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
@Service
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
        userSearch.setUserId(socialNetworkAPI.getCurrentUserId());
        return userSearch;
    }

    public UserSearch fromUser(String userId) {
        userSearch.setUserId(userId);
        return userSearch;
    }

    public PostSearch fromPost() {
        return new PostSearch();
    }

    public CommunitySearch fromCommunity() {
        return new CommunitySearch();
    }
}
