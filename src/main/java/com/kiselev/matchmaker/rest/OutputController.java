package com.kiselev.matchmaker.rest;

import com.google.common.collect.Lists;
import com.kiselev.matchmaker.api.model.Group;
import com.kiselev.matchmaker.api.model.Post;
import com.kiselev.matchmaker.api.model.User;
import com.kiselev.matchmaker.search.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Temporary rest service for testing of Java API
 *
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
@RestController
@RequestMapping("/api")
public class OutputController {

    @Autowired
    private Search search;

    // User
    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public List<User> user() {
        return search
                .fromUsers(IntStream.rangeClosed(1, 1000)
                        .boxed()
                        .map(Object::toString)
                        .collect(Collectors.toList()))
                .perform();
    }

    @RequestMapping(path = "/friends", method = RequestMethod.GET)
    public List<User> friends() {
        return search
                .fromUser("3837686")
                .friends()
                .where(null)
                .perform();
    }

    @RequestMapping(path = "/followers", method = RequestMethod.GET)
    public List<User> followers() {
        return search
                .fromUser("42597474")
                .followers()
                .where(null)
                .perform();
    }

    @RequestMapping(path = "/subscriptions", method = RequestMethod.GET)
    public List<User> subscriptions() {
        return search
                .fromUser("42597474")
                .subscriptions()
                .where(null)
                .perform();
    }

    @RequestMapping(path = "/posts", method = RequestMethod.GET)
    public List<Post> posts() {
        return search
                .fromPosts(Lists.newArrayList("42597474_4424", "42597474_4426", "280512823_193", "177079109_326", "177079109_329"))
                .perform();
    }

    @RequestMapping(path = "/groups", method = RequestMethod.GET)
    public List<Group> groups() {
        return search
                .fromUser("3837686")
                .groups()
                .where(null)
                .perform();
    }

    // Post
    @RequestMapping(path = "/likes", method = RequestMethod.GET)
    public List<User> likes() {
        return search
                .fromPost("42597474_4424")
                .likes()
                .where(null)
                .perform();
    }

    @RequestMapping(path = "/shares", method = RequestMethod.GET)
    public List<User> shares() {
        return search
                .fromPost("42597474_4424")
                .shares()
                .where(null)
                .perform();
    }

    // Groups
    @RequestMapping(path = "/subscribers", method = RequestMethod.GET)
    public List<User> subscribers() {
        return search
                .fromGroup("112233021")
                .subscribers()
                .where(null)
                .perform();
    }

    @RequestMapping(path = "/groupPosts", method = RequestMethod.GET)
    public List<Post> groupPosts() {
        return search
                .fromGroup("-112233021")
                .posts()
                .where(null)
                .perform();
    }
}
