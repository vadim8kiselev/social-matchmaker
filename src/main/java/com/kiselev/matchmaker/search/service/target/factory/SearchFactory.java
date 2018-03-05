package com.kiselev.matchmaker.search.service.target.factory;

import com.kiselev.matchmaker.search.service.target.general.GeneralGroupSearch;
import com.kiselev.matchmaker.search.service.target.general.GeneralPostSearch;
import com.kiselev.matchmaker.search.service.target.general.GeneralUserSearch;
import org.springframework.beans.factory.annotation.Autowired;

public class SearchFactory {

    @Autowired
    private GeneralUserSearch userSearch;

    @Autowired
    private GeneralPostSearch postSearch;

    @Autowired
    private GeneralGroupSearch groupSearch;

    public GeneralUserSearch userSearch() {
        return userSearch;
    }

    public GeneralPostSearch postSearch() {
        return postSearch;
    }

    public GeneralGroupSearch groupSearch() {
        return groupSearch;
    }
}
