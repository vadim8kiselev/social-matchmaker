package com.kiselev.matchmaker.search.service.contract;

import com.kiselev.matchmaker.search.service.concept.PostSearchConcept;
import com.kiselev.matchmaker.search.service.concept.UserSearchConcept;
import com.kiselev.matchmaker.search.service.contract.general.GeneralSearchContract;

public interface GroupSearchContract extends GeneralSearchContract {

    UserSearchConcept subscribers();

    PostSearchConcept posts();
}
