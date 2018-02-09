package com.kiselev.matchmaker.search.response.nonfiltered;

import com.kiselev.matchmaker.api.model.User;
import com.kiselev.matchmaker.search.condition.Condition;
import com.kiselev.matchmaker.search.response.ExecutableResponse;
import com.kiselev.matchmaker.search.response.FilteredExecutableResponse;

import java.util.List;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
public class NonFilteredUserResponse implements ExecutableResponse<User> {

    private List<User> users;

    public NonFilteredUserResponse(List<User> users) {
        this.users = users;
    }

    public FilteredExecutableResponse where(Condition condition) {
        return new FilteredExecutableResponse();
    }

    public List<User> execute() {
        return users;
    }
}
