package com.kiselev.matchmaker.rest;

import com.kiselev.matchmaker.api.model.Group;
import com.kiselev.matchmaker.api.model.Post;
import com.kiselev.matchmaker.api.model.User;
import com.kiselev.matchmaker.search.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
@RestController
@RequestMapping("/api")
public class OutputController {

    @Autowired
    private Search search;

    // User
    @RequestMapping(path = "/friends", method = RequestMethod.GET)
    public List<User> friends() {
        return search
                .fromUser("446299337")
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
                .fromUser("42597474")
                .posts()
                .where(null)
                .perform();
    }

    @RequestMapping(path = "/groups", method = RequestMethod.GET)
    public List<Group> groups() {
        return search
                .fromUser("42597474")
                .groups()
                .where(null)
                .perform();
    }

    // Post
    @RequestMapping(path = "/likers", method = RequestMethod.GET)
    public List<User> likers() {
        return search
                .fromPost("42597474_4424")
                .likers()
                .where(null)
                .perform();
    }

    @RequestMapping(path = "/sharers", method = RequestMethod.GET)
    public List<User> sharers() {
        return search
                .fromPost("42597474_4424")
                .sharers()
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
