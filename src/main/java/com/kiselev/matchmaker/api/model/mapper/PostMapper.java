package com.kiselev.matchmaker.api.model.mapper;

import com.kiselev.matchmaker.api.model.Mapper;

public enum PostMapper implements Mapper {

    ID("id"),
    FROM_ID("fromId"),
    OWNER_ID("ownerId"),
    TEXT("text"),
    DATE("date"),

    LIKES_COUNT("likesCount"),
    SHARES_COUNT("sharesCount"),
    VIEWS_COUNT("viewsCount"),
    COMMENTS_COUNT("commentsCount"),

    GEO("geo"),
    ATTACHMENTS("attachments"),

    TYPE("type"),
    PLATFORM("platform");

    private String field;

    PostMapper(String field) {
        this.field = field;
    }

    @Override
    public String field() {
        return field;
    }
}
