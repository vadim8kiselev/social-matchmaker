package com.kiselev.matchmaker.search.service.target.general;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kiselev.matchmaker.search.service.concept.GroupSearchConcept;
import com.kiselev.matchmaker.search.service.contract.GroupSearchContract;
import com.kiselev.matchmaker.search.service.target.implementation.GroupSearch;

@JsonDeserialize(as = GroupSearch.class)
public interface GeneralGroupSearch extends GroupSearchContract, GroupSearchConcept {
}
