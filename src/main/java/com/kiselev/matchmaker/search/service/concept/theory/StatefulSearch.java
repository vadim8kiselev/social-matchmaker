package com.kiselev.matchmaker.search.service.concept.theory;

import com.kiselev.matchmaker.api.model.Entity;

import java.util.List;

public interface StatefulSearch<Search extends StatefulSearch, Pojo extends Entity> {

    Search fromEntities(List<Pojo> entities);

    Search from(List<String> entitiesIds);
}
