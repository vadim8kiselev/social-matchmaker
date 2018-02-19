package com.kiselev.matchmaker.search.service.contract;

import com.kiselev.matchmaker.search.service.concept.UserSearchConcept;
import com.kiselev.matchmaker.search.service.contract.general.GeneralSearchContract;

public interface PostSearchContract extends GeneralSearchContract {

    UserSearchConcept likes();

    UserSearchConcept shares();
}
