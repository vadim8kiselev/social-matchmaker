package com.kiselev.matchmaker.statistics.db.dao;

import com.kiselev.matchmaker.api.model.entity.Group;
import com.kiselev.matchmaker.api.model.entity.Post;
import com.kiselev.matchmaker.api.model.entity.User;

public interface DAO {

    void saveUsers(Iterable<User> users);

    Iterable<User> findUsers();

    void savePosts(Iterable<Post> posts);

    Iterable<Post> findPosts();

    void saveGroups(Iterable<Group> groups);

    Iterable<Group> findGroups();
}
