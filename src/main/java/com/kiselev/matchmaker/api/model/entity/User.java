package com.kiselev.matchmaker.api.model.entity;

import lombok.Getter;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author: Vadim Kiselev
 * @date: 09.02.2018
 */
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@EqualsAndHashCode(callSuper = false)
public class User extends AbstractEntity {

    @Id
    @Column(name = "user_id")
    private String id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "screen_name")
    private String screenName;

    @Column(name = "sex")
    private String sex;

    @Column(name = "online")
    private String online;

    @Column(name = "birthday")
    private String birthday;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "mobile_phone")
    private String mobilePhone;

    @Column(name = "home_phone")
    private String homePhone;


    @Column(name = "skype")
    private String skype;

    @Column(name = "facebook")
    private String facebook;

    @Column(name = "twitter")
    private String twitter;

    @Column(name = "livejournal")
    private String livejournal;

    @Column(name = "instagram")
    private String instagram;


    @Column(name = "status")
    private String status;

    @Column(name = "last_seen")
    private String lastSeen;


    @Column(name = "career")
    private String career;

    @Column(name = "military")
    private String military;

    @Column(name = "university")
    private String university;

    @Column(name = "home_town")
    private String homeTown;


    @Column(name = "photo_link")
    private String photoLink;


    @Column(name = "relation")
    private String relation;

    @Column(name = "relation_partner")
    private String relationPartner;


    @Column(name = "interests")
    private String interests;

    @Column(name = "music")
    private String music;

    @Column(name = "activities")
    private String activities;

    @Column(name = "movies")
    private String movies;

    @Column(name = "tv")
    private String tv;

    @Column(name = "books")
    private String books;

    @Column(name = "games")
    private String games;

    @Column(name = "schools")
    private String schools;

    @Column(name = "about")
    private String about;

    @Column(name = "quotes")
    private String quotes;


    @Column(name = "political")
    private String political;

    @Column(name = "languages")
    private String languages;

    @Column(name = "religion")
    private String religion;

    @Column(name = "inspired_by")
    private String inspiredBy;

    @Column(name = "people_main")
    private String peopleMain;

    @Column(name = "lifeMain")
    private String lifeMain;

    @Column(name = "smoking")
    private String smoking;

    @Column(name = "alcohol")
    private String alcohol;


    @Column(name = "deactivated")
    private String deactivated;
}
