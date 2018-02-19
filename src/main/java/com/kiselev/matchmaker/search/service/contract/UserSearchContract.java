package com.kiselev.matchmaker.search.service.contract;

import com.kiselev.matchmaker.search.service.concept.GroupSearchConcept;
import com.kiselev.matchmaker.search.service.concept.PostSearchConcept;
import com.kiselev.matchmaker.search.service.concept.UserSearchConcept;
import com.kiselev.matchmaker.search.service.contract.general.GeneralSearchContract;

public interface UserSearchContract extends GeneralSearchContract {

    UserSearchConcept friends();

    UserSearchConcept followers();

    UserSearchConcept subscriptions();

    PostSearchConcept posts();

    GroupSearchConcept groups();
}
