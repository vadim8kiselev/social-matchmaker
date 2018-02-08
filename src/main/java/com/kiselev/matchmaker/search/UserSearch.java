package com.kiselev.matchmaker.search;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
public class UserSearch {

    private String userId;

    public NonFilteredResponse friends() {
        return new NonFilteredResponse();
    }

    public NonFilteredResponse followers() {
        return new NonFilteredResponse();
    }

    public NonFilteredResponse subscriptions() {
        return new NonFilteredResponse();
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
