package com.kiselev.matchmaker.search.service.concept;

import com.kiselev.matchmaker.api.model.entity.User;
import com.kiselev.matchmaker.search.service.concept.general.GeneralSearchConcept;
import com.kiselev.matchmaker.search.service.contract.UserSearchContract;
import com.kiselev.matchmaker.search.service.target.general.GeneralUserSearch;

public interface UserSearchConcept extends GeneralSearchConcept<GeneralUserSearch, UserSearchConcept, UserSearchContract, User> {
}
