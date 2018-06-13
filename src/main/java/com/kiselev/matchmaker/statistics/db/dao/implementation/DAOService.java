package com.kiselev.matchmaker.statistics.db.dao.implementation;

import com.google.common.collect.Lists;
import com.kiselev.matchmaker.api.model.entity.Group;
import com.kiselev.matchmaker.api.model.entity.Post;
import com.kiselev.matchmaker.api.model.entity.User;
import com.kiselev.matchmaker.statistics.db.dao.DAO;
import com.kiselev.matchmaker.statistics.db.repository.GroupRepository;
import com.kiselev.matchmaker.statistics.db.repository.PostRepository;
import com.kiselev.matchmaker.statistics.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

public class DAOService implements DAO {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private GroupRepository groupRepository;

    public void saveUsers(Iterable<User> users) {
        try {
            userRepository.save(filterDeactivatedUsers(users));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public Iterable<User> findUsers() {
        try {
            return userRepository.findAll();
        } catch (Exception exception) {
            exception.printStackTrace();
            return Lists.newArrayList();
        }
    }

    public void savePosts(Iterable<Post> posts) {
        try {
            postRepository.save(posts);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public Iterable<Post> findPosts() {
        try {
            return postRepository.findAll();
        } catch (Exception exception) {
            exception.printStackTrace();
            return Lists.newArrayList();
        }
    }

    public void saveGroups(Iterable<Group> groups) {
        try {
            groupRepository.save(groups);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public Iterable<Group> findGroups() {
        try {
            return groupRepository.findAll();
        } catch (Exception exception) {
            exception.printStackTrace();
            return Lists.newArrayList();
        }
    }

    private Iterable<User> filterDeactivatedUsers(Iterable<User> users) {
        return Lists.newArrayList(users).stream()
                .filter(user -> user.getDeactivated().isEmpty())
                .collect(Collectors.toList());
    }
}
