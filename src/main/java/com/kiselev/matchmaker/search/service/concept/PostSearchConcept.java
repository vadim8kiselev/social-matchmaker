package com.kiselev.matchmaker.search.service.concept;

import com.kiselev.matchmaker.api.model.entity.Post;
import com.kiselev.matchmaker.search.service.concept.general.GeneralSearchConcept;
import com.kiselev.matchmaker.search.service.contract.PostSearchContract;
import com.kiselev.matchmaker.search.service.target.general.GeneralPostSearch;

public interface PostSearchConcept extends GeneralSearchConcept<GeneralPostSearch, PostSearchConcept, PostSearchContract, Post> {
}
