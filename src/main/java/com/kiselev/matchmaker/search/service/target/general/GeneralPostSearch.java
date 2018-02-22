package com.kiselev.matchmaker.search.service.target.general;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kiselev.matchmaker.search.service.concept.PostSearchConcept;
import com.kiselev.matchmaker.search.service.contract.PostSearchContract;
import com.kiselev.matchmaker.search.service.target.implementation.PostSearch;

@JsonDeserialize(as = PostSearch.class)
public interface GeneralPostSearch extends PostSearchContract, PostSearchConcept {
}
