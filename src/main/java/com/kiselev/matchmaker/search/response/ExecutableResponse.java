package com.kiselev.matchmaker.search.response;

import java.util.List;

public interface ExecutableResponse<Pojo> {

    List<Pojo> execute();
}
