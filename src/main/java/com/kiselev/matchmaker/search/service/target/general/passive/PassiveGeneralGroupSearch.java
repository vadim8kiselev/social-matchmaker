package com.kiselev.matchmaker.search.service.target.general.passive;

import com.kiselev.matchmaker.search.service.concept.PostSearchConcept;
import com.kiselev.matchmaker.search.service.concept.UserSearchConcept;
import com.kiselev.matchmaker.search.service.target.general.GeneralGroupSearch;

public interface PassiveGeneralGroupSearch extends GeneralGroupSearch {

    void setUserSearch(UserSearchConcept userSearch);

    void setPostSearch(PostSearchConcept postSearch);
}
