package com.kiselev.matchmaker.statistics;

import com.kiselev.matchmaker.api.model.entity.Group;
import com.kiselev.matchmaker.api.model.entity.Post;
import com.kiselev.matchmaker.api.model.entity.User;

import java.util.List;

public interface StatisticsService {

    void postUsers(List<User> users);

    void postPosts(List<Post> posts);

    void postGroups(List<Group> groups);
}
