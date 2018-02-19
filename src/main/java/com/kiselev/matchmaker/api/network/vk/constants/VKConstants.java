package com.kiselev.matchmaker.api.network.vk.constants;

import com.kiselev.matchmaker.api.network.vk.annotation.Doc;

public class VKConstants {

    public static class MaxCount {

        @Doc(method = "wall.get")
        public static final Integer WALL_GET = 100;

        @Doc(method = "wall.getById")
        public static final Integer WALL_GET_BY_ID = 100;

        @Doc(method = "users.getSubscriptions")
        public static final Integer USERS_GET_SUBSCRIPTIONS = 200;

        @Doc(method = "users.get")
        public static final Integer USERS_GET = 1000;

        @Doc(method = "groups.get")
        public static final Integer GROUPS_GET = 1000;

        @Doc(method = "likes.getList")
        public static final Integer LIKES_GET_LIST = 1000;

        @Doc(method = "wall.getReposts")
        public static final Integer WALL_GET_REPOSTS = 1000;

        @Doc(method = "groups.getById")
        public static final Integer GROUPS_GET_BY_ID = 1000;

        @Doc(method = "groups.getMembers")
        public static final Integer GROUPS_GET_MEMBERS = 1000;

        @Doc(method = "users.getFollowers")
        public static final Integer USERS_GET_FOLLOWERS = 1000;

        @Doc(method = "friends.get")
        public static final Integer FRIENDS_GET = 5000;

    }
}
