package com.kiselev.matchmaker.api.model;

import lombok.Builder;
import lombok.Getter;

/**
 * @author: Vadim Kiselev
 * @date: 09.02.2018
 */
@Builder
@Getter
public class Group {
    private String id;
    private String name;
    private String screenName;
    private String status;
    private String description;

    private String type;
    private String verified;
    private String isClosed;
    private String ageLimits;
    private String subscribersCount;

    private String city;
    private String country;

    private String photoLink;

    private String links;
    private String contacts;
    private String site;

    private String deactivated;
}
