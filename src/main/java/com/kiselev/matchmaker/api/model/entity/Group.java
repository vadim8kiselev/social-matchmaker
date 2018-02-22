package com.kiselev.matchmaker.api.model.entity;

import lombok.Getter;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * @author: Vadim Kiselev
 * @date: 09.02.2018
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Group extends AbstractEntity {
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


