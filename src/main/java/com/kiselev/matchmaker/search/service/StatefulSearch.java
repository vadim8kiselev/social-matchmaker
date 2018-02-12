package com.kiselev.matchmaker.search.service;

import java.util.List;

public interface StatefulSearch {

    StatefulSearch from(String... data);

    StatefulSearch from(List<String> data);
}
