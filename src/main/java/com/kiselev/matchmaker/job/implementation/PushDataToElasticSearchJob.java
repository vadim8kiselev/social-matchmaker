package com.kiselev.matchmaker.job.implementation;

import com.google.common.collect.Lists;
import com.kiselev.matchmaker.api.model.entity.Group;
import com.kiselev.matchmaker.api.model.entity.Post;
import com.kiselev.matchmaker.api.model.entity.User;
import com.kiselev.matchmaker.job.Job;
import com.kiselev.matchmaker.statistics.StatisticsService;
import com.kiselev.matchmaker.statistics.db.dao.DAO;
import com.kiselev.matchmaker.statistics.db.repository.GroupRepository;
import com.kiselev.matchmaker.statistics.db.repository.PostRepository;
import com.kiselev.matchmaker.statistics.db.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

public class PushDataToElasticSearchJob implements Job {

    @Autowired
    private DAO dao;

    @Autowired
    private StatisticsService statisticsService;

    private Iterable<User> users;
    private Iterable<Post> posts;
    private Iterable<Group> groups;

    @Override
    @PostConstruct
    public void start() {
        new Thread(this::run).start();
    }

    private void run() {
        while (true) {
            postUsers();
            postPosts();
            postGroups();
            sleep();
        }
    }

    private void postUsers() {
        List<User> users = Lists.newArrayList(dao.findUsers());
        if (!users.equals(this.users)) {
            statisticsService.postUsers(users);
            this.users = users;
        }
    }

    private void postPosts() {
        List<Post> posts = Lists.newArrayList(dao.findPosts());
        if (!posts.equals(this.posts)) {
            statisticsService.postPosts(posts);
            this.posts = posts;
        }
    }

    private void postGroups() {
        List<Group> groups = Lists.newArrayList(dao.findGroups());
        if (!groups.equals(this.groups)) {
            statisticsService.postGroups(groups);
            this.groups = groups;
        }
    }

    private void sleep() {
        try {
            Thread.sleep(60 * 1000); // 1 minute
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
