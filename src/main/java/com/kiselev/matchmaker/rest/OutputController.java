package com.kiselev.matchmaker.rest;

import com.kiselev.matchmaker.api.model.Community;
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

    @RequestMapping(path = "/friends", method = RequestMethod.GET)
    public List<User> friends() {
        return search
                .fromMe()
                .friends()
                .execute();
    }

    @RequestMapping(path = "/posts", method = RequestMethod.GET)
    public List<Post> posts() {
        return search
                .fromMe()
                .posts()
                .execute();
    }

    @RequestMapping(path = "/communities", method = RequestMethod.GET)
    public List<Community> communities() {
        return search
                .fromMe()
                .communities()
                .execute();
    }
}
