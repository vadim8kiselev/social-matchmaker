package com.kiselev.matchmaker.search.service.contract;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kiselev.matchmaker.search.service.concept.UserSearchConcept;
import com.kiselev.matchmaker.search.service.contract.general.GeneralSearchContract;
import com.kiselev.matchmaker.search.service.target.implementation.PostSearch;

@JsonDeserialize(as = PostSearch.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public interface PostSearchContract extends GeneralSearchContract<PostSearchContract> {

    UserSearchConcept likes();

    UserSearchConcept shares();
}
