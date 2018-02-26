package com.kiselev.matchmaker.search.service.concept;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kiselev.matchmaker.api.model.entity.User;
import com.kiselev.matchmaker.search.service.concept.general.GeneralSearchConcept;
import com.kiselev.matchmaker.search.service.contract.UserSearchContract;
import com.kiselev.matchmaker.search.service.target.general.GeneralUserSearch;
import com.kiselev.matchmaker.search.service.target.implementation.UserSearch;

@JsonDeserialize(as = UserSearch.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public interface UserSearchConcept extends GeneralSearchConcept<GeneralUserSearch, UserSearchConcept, UserSearchContract, User> {
}
