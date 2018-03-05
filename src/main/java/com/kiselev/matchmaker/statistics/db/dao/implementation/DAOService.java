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
        userRepository.save(filterDeactivatedUsers(users));
    }

    public void savePosts(Iterable<Post> posts) {
        postRepository.save(posts);
    }

    public void saveGroups(Iterable<Group> groups) {
        groupRepository.save(groups);
    }

    private Iterable<User> filterDeactivatedUsers(Iterable<User> users) {
        return Lists.newArrayList(users).stream()
                .filter(user -> user.getDeactivated().isEmpty())
                .collect(Collectors.toList());
    }
}
