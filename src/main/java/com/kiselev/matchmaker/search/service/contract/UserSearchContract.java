package com.kiselev.matchmaker.search.service.contract;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kiselev.matchmaker.search.service.concept.GroupSearchConcept;
import com.kiselev.matchmaker.search.service.concept.PostSearchConcept;
import com.kiselev.matchmaker.search.service.concept.UserSearchConcept;
import com.kiselev.matchmaker.search.service.contract.general.GeneralSearchContract;
import com.kiselev.matchmaker.search.service.target.implementation.UserSearch;

@JsonDeserialize(as = UserSearch.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public interface UserSearchContract extends GeneralSearchContract<UserSearchContract> {

    UserSearchConcept friends();

    UserSearchConcept followers();

    UserSearchConcept subscriptions();

    PostSearchConcept posts();

    GroupSearchConcept groups();
}
