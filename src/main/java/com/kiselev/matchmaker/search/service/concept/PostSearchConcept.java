package com.kiselev.matchmaker.search.service.concept;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kiselev.matchmaker.api.model.entity.Post;
import com.kiselev.matchmaker.search.service.concept.general.GeneralSearchConcept;
import com.kiselev.matchmaker.search.service.contract.PostSearchContract;
import com.kiselev.matchmaker.search.service.target.general.GeneralPostSearch;
import com.kiselev.matchmaker.search.service.target.implementation.PostSearch;

@JsonDeserialize(as = PostSearch.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public interface PostSearchConcept extends GeneralSearchConcept<GeneralPostSearch, PostSearchConcept, PostSearchContract, Post> {
}
