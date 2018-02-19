package com.kiselev.matchmaker.search.service.concept.general;

import com.kiselev.matchmaker.api.model.Entity;
import com.kiselev.matchmaker.search.service.concept.theory.FilterableSearch;
import com.kiselev.matchmaker.search.service.concept.theory.PerformableSearch;
import com.kiselev.matchmaker.search.service.concept.theory.RepeatableSearch;
import com.kiselev.matchmaker.search.service.concept.theory.StatefulSearch;
import com.kiselev.matchmaker.search.service.contract.general.GeneralSearchContract;

public interface GeneralSearchConcept<General extends GeneralSearchConcept & GeneralSearchContract, SearchConcept extends GeneralSearchConcept, SearchContract extends GeneralSearchContract, Pojo extends Entity>
        extends StatefulSearch<General, Pojo>, FilterableSearch<SearchConcept>, RepeatableSearch<SearchContract>, PerformableSearch<Pojo> {
}
