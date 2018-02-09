package com.kiselev.matchmaker.search.condition;

import java.util.List;
import java.util.Map;

public class Condition {

    private List<Map<String, String>> filters;

    public List<Map<String, String>> getFilters() {
        return filters;
    }

    public void setFilters(List<Map<String, String>> filters) {
        this.filters = filters;
    }
}
