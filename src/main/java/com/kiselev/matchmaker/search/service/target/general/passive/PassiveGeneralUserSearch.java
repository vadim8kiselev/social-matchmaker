package com.kiselev.matchmaker.search.service.target.general.passive;

import com.kiselev.matchmaker.search.service.concept.GroupSearchConcept;
import com.kiselev.matchmaker.search.service.concept.PostSearchConcept;
import com.kiselev.matchmaker.search.service.target.general.GeneralUserSearch;

public interface PassiveGeneralUserSearch extends GeneralUserSearch {

    void setPostSearch(PostSearchConcept postSearch);

    void setGroupSearch(GroupSearchConcept groupSearch);
}
