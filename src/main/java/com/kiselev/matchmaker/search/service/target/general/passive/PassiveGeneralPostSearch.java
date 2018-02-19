package com.kiselev.matchmaker.search.service.target.general.passive;

import com.kiselev.matchmaker.search.service.concept.UserSearchConcept;
import com.kiselev.matchmaker.search.service.target.general.GeneralPostSearch;

public interface PassiveGeneralPostSearch extends GeneralPostSearch {

    void setUserSearch(UserSearchConcept userSearch);
}
