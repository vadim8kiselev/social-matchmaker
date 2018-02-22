package com.kiselev.matchmaker.search.service.target.factory;

import com.kiselev.matchmaker.search.service.target.implementation.GroupSearch;
import com.kiselev.matchmaker.search.service.target.implementation.PostSearch;
import com.kiselev.matchmaker.search.service.target.implementation.UserSearch;
import org.springframework.beans.factory.annotation.Autowired;

public class SearchFactory {

    @Autowired
    private UserSearch userSearch;

    @Autowired
    private PostSearch postSearch;

    @Autowired
    private GroupSearch groupSearch;

    public UserSearch userSearch() {
        return userSearch;
    }

    public PostSearch postSearch() {
        return postSearch;
    }

    public GroupSearch groupSearch() {
        return groupSearch;
    }
}
