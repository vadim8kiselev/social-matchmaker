package com.kiselev.matchmaker.api.model.mapper;

import com.kiselev.matchmaker.api.model.Mapper;

public enum UserMapper implements Mapper {

    ID("id"),
    FIRST_NAME("firstName"),
    LAST_NAME("lastName"),
    SCREEN_NAME("screenName"),
    SEX("sex"),
    ONLINE("online"),
    BIRTHDAY("birthday"),
    CITY("city"),
    COUNTRY("country"),
    MOBILE_PHONE("mobilePhone"),
    HOME_PHONE("homePhone"),

    SKYPE("skype"),
    FACEBOOK("facebook"),
    TWITTER("twitter"),
    LIVEJOURNAL("livejournal"),
    INSTAGRAM("instagram"),

    STATUS("status"),
    LAST_SEEN("lastSeen"),

    CAREER("career"),
    MILITARY("military"),
    UNIVERSITY("university"),
    HOMETOWN("homeTown"),

    PHOTO_LINK("photoLink"),

    RELATION("relation"),
    RELATION_PARTNER("relationPartner"),

    INTERESTS("interests"),
    MUSIC("music"),
    ACTIVITIES("activities"),
    MOVIES("movies"),
    TV("tv"),
    BOOKS("books"),
    GAMES("games"),
    SCHOOLS("schools"),
    ABOUT("about"),
    QUOTES("quotes"),

    POLITICAL("political"),
    LANGUAGES("languages"),
    RELIGION("religion"),
    INSPIRED_BY("inspiredBy"),
    PEOPLE_MAIN("peopleMain"),
    LIFE_MAIN("lifeMain"),
    SMOKING("smoking"),
    ALCOHOL("alcohol"),

    DEACTIVATED("deactivated");

    private String field;

    UserMapper(String field) {
        this.field = field;
    }

    @Override
    public String field() {
        return field;
    }
}
