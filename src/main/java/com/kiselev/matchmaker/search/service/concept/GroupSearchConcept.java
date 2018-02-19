package com.kiselev.matchmaker.search.service.concept;

import com.kiselev.matchmaker.api.model.entity.Group;
import com.kiselev.matchmaker.search.service.concept.general.GeneralSearchConcept;
import com.kiselev.matchmaker.search.service.contract.GroupSearchContract;
import com.kiselev.matchmaker.search.service.target.general.GeneralGroupSearch;

public interface GroupSearchConcept extends GeneralSearchConcept<GeneralGroupSearch, GroupSearchConcept, GroupSearchContract, Group> {
}
