package com.kiselev.matchmaker.search.service.target.general;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kiselev.matchmaker.search.service.concept.UserSearchConcept;
import com.kiselev.matchmaker.search.service.contract.UserSearchContract;
import com.kiselev.matchmaker.search.service.target.implementation.UserSearch;

@JsonDeserialize(as = UserSearch.class)
public interface GeneralUserSearch extends UserSearchContract, UserSearchConcept {
}
