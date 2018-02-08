package com.kiselev.matchmaker.rest;

import com.kiselev.matchmaker.api.SocialNetworkAPI;
import com.kiselev.matchmaker.api.model.User;
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
    private SocialNetworkAPI socialNetworkAPI;

    @RequestMapping(path = "/friends", method = RequestMethod.GET)
    public List<User> friends() {
        return socialNetworkAPI.getFriendsByUserId(42597474);
    }
}
