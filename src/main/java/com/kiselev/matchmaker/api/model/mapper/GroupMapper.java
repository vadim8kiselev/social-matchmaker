package com.kiselev.matchmaker.api.model.mapper;

import com.kiselev.matchmaker.api.model.Mapper;

public enum GroupMapper implements Mapper {

    ID("id"),
    NAME("name"),
    SCREEN_NAME("screenName"),
    STATUS("status"),
    DESCRIPTION("description"),

    TYPE("type"),
    VERIFIED("verified"),
    IS_CLOSED("isClosed"),
    AGE_LIMITS("ageLimits"),
    SUBSCRIBERS_COUNT("subscribersCount"),

    CITY("city"),
    COUNTRY("country"),

    PHOTO_LINK("photoLink"),

    LINKS("links"),
    CONTACTS("contacts"),
    SITE("site"),

    DEACTIVATED("deactivated");

    private String field;

    GroupMapper(String field) {
        this.field = field;
    }

    @Override
    public String field() {
        return field;
    }
}
