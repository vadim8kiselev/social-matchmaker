package com.kiselev.matchmaker.search.service.concept.theory;

import com.kiselev.matchmaker.search.service.contract.general.GeneralSearchContract;

@FunctionalInterface
public interface RepeatableSearch<Search extends GeneralSearchContract> {

    Search then();
}
