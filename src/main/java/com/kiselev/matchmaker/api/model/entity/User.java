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
public class User extends AbstractEntity {
    private String id;
    private String firstName;
    private String lastName;
    private String screenName;
    private String sex;
    private String online;
    private String birthday;
    private String city;
    private String country;
    private String mobilePhone;
    private String homePhone;

    private String skype;
    private String facebook;
    private String twitter;
    private String livejournal;
    private String instagram;

    private String status;
    private String lastSeen;

    private String career;
    private String military;
    private String university;
    private String homeTown;

    private String photoLink;

    private String relation;
    private String relationPartner;

    private String interests;
    private String music;
    private String activities;
    private String movies;
    private String tv;
    private String books;
    private String games;
    private String schools;
    private String about;
    private String quotes;

    private String political;
    private String languages;
    private String religion;
    private String inspiredBy;
    private String peopleMain;
    private String lifeMain;
    private String smoking;
    private String alcohol;

    private String deactivated;
}
