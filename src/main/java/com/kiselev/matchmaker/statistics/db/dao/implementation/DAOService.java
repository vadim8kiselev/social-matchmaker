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

    private boolean fallback = true;

    public void saveUsers(Iterable<User> users) {
        try {
            if (this.fallback) {
                userRepository.save(filterDeactivatedUsers(users));
            }
        } catch (Exception exception) {
            this.fallback = false;
        }
    }

    @Override
    public Iterable<User> findUsers() {
        try {
            if (this.fallback) {
                return userRepository.findAll();
            }
        } catch (Exception exception) {
            this.fallback = false;
        }

        return Lists.newArrayList();
    }

    public void savePosts(Iterable<Post> posts) {
        try {
            if (this.fallback) {
                postRepository.save(posts);
            }
        } catch (Exception exception) {
            this.fallback = false;
        }
    }

    @Override
    public Iterable<Post> findPosts() {
        try {
            if (this.fallback) {
                return postRepository.findAll();
            }
        } catch (Exception exception) {
            this.fallback = false;
        }

        return Lists.newArrayList();
    }

    public void saveGroups(Iterable<Group> groups) {
        try {
            if (this.fallback) {
                groupRepository.save(groups);
            }
        } catch (Exception exception) {
            this.fallback = false;
        }
    }

    @Override
    public Iterable<Group> findGroups() {
        try {
            if (this.fallback) {
                return groupRepository.findAll();
            }
        } catch (Exception exception) {
            this.fallback = false;
        }

        return Lists.newArrayList();
    }

    private Iterable<User> filterDeactivatedUsers(Iterable<User> users) {
        return Lists.newArrayList(users).stream()
                .filter(user -> user.getDeactivated().isEmpty())
                .collect(Collectors.toList());
    }
}
