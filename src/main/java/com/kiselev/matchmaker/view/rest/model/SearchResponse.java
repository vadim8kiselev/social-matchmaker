package com.kiselev.matchmaker.view.rest.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.kiselev.matchmaker.search.service.target.implementation.GroupSearch;
import com.kiselev.matchmaker.search.service.target.implementation.PostSearch;
import com.kiselev.matchmaker.search.service.target.implementation.UserSearch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchResponse<SearchType> {

    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
    @JsonSubTypes({
            @JsonSubTypes.Type(value = UserSearch.class, name = "UserSearch"),
            @JsonSubTypes.Type(value = PostSearch.class, name = "PostSearch"),
            @JsonSubTypes.Type(value = GroupSearch.class, name = "GroupSearch")})
    private SearchType search;

    private List<AvailableMethod> methods;

    public static <SearchType> SearchResponse of(SearchType search, List<AvailableMethod> methods) {
        return new SearchResponse<>(search, methods);
    }
}
