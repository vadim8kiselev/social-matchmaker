package com.kiselev.matchmaker.view.rest.implementation;

import com.kiselev.matchmaker.api.model.entity.Group;
import com.kiselev.matchmaker.api.model.entity.Post;
import com.kiselev.matchmaker.api.model.entity.User;
import com.kiselev.matchmaker.search.service.Search;
import com.kiselev.matchmaker.search.service.concept.GroupSearchConcept;
import com.kiselev.matchmaker.search.service.concept.PostSearchConcept;
import com.kiselev.matchmaker.search.service.concept.UserSearchConcept;
import com.kiselev.matchmaker.search.service.contract.GroupSearchContract;
import com.kiselev.matchmaker.search.service.contract.PostSearchContract;
import com.kiselev.matchmaker.search.service.contract.UserSearchContract;
import com.kiselev.matchmaker.search.service.target.general.GeneralGroupSearch;
import com.kiselev.matchmaker.search.service.target.general.GeneralPostSearch;
import com.kiselev.matchmaker.search.service.target.general.GeneralUserSearch;
import com.kiselev.matchmaker.view.rest.RestView;
import com.kiselev.matchmaker.view.rest.model.PerformResponse;
import com.kiselev.matchmaker.view.rest.model.Response;
import com.kiselev.matchmaker.view.rest.resolver.MethodResolver;
import com.kiselev.matchmaker.view.serialize.SerializeView;
import com.kiselev.matchmaker.view.serialize.resolver.SerializeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
@RestController
@RequestMapping("/api")
public class RestViewController implements RestView {

    @Autowired
    private Search search;

    @Autowired
    private SerializeResolver serializeResolver;

    @Override
    @RequestMapping(path = "/start", method = RequestMethod.GET)
    public Response start() {
        return Response.of(null, MethodResolver.availableMethodsOf(Search.class));
    }

    @Override
    @RequestMapping(path = "/fromUser", method = RequestMethod.GET)
    public Response fromUser(@RequestParam("userId") String userId) {
        GeneralUserSearch userSearch = search.fromUser(userId);
        return Response.of(userSearch, MethodResolver.availableMethodsOf(GeneralUserSearch.class));
    }

    @Override
    @RequestMapping(path = "/fromUsers", method = RequestMethod.GET)
    public Response fromUsers(@RequestParam("usersIds") List<String> usersIds) {
        GeneralUserSearch userSearch = search.fromUsers(usersIds);
        return Response.of(userSearch, MethodResolver.availableMethodsOf(GeneralUserSearch.class));
    }

    @Override
    @RequestMapping(path = "/fromPost", method = RequestMethod.GET)
    public Response fromPost(@RequestParam("postId") String postId) {
        GeneralPostSearch postSearch = search.fromPost(postId);
        return Response.of(postSearch, MethodResolver.availableMethodsOf(GeneralPostSearch.class));
    }

    @Override
    @RequestMapping(path = "/fromPosts", method = RequestMethod.GET)
    public Response fromPosts(@RequestParam("postsIds") List<String> postsIds) {
        GeneralPostSearch postSearch = search.fromPosts(postsIds);
        return Response.of(postSearch, MethodResolver.availableMethodsOf(GeneralPostSearch.class));
    }

    @Override
    @RequestMapping(path = "/fromGroup", method = RequestMethod.GET)
    public Response fromGroup(@RequestParam("groupId") String groupId) {
        GeneralGroupSearch groupSearch = search.fromGroup(groupId);
        return Response.of(groupSearch, MethodResolver.availableMethodsOf(GeneralGroupSearch.class));
    }

    @Override
    @RequestMapping(path = "/fromGroups", method = RequestMethod.GET)
    public Response fromGroups(@RequestParam("groupsIds") List<String> groupsIds) {
        GeneralGroupSearch groupSearch = search.fromGroups(groupsIds);
        return Response.of(groupSearch, MethodResolver.availableMethodsOf(GeneralGroupSearch.class));
    }


    @Override
    @RequestMapping(path = "/user/friends", method = RequestMethod.POST)
    public Response friends(@RequestBody UserSearchContract userSearch) {
        UserSearchConcept friends = userSearch.friends();
        return Response.of(friends, MethodResolver.availableMethodsOf(UserSearchConcept.class));
    }

    @Override
    @RequestMapping(path = "/user/followers", method = RequestMethod.POST)
    public Response followers(@RequestBody UserSearchContract userSearch) {
        UserSearchConcept followers = userSearch.followers();
        return Response.of(followers, MethodResolver.availableMethodsOf(UserSearchConcept.class));
    }

    @Override
    @RequestMapping(path = "/user/subscriptions", method = RequestMethod.POST)
    public Response subscriptions(@RequestBody UserSearchContract userSearch) {
        UserSearchConcept subscriptions = userSearch.subscriptions();
        return Response.of(subscriptions, MethodResolver.availableMethodsOf(UserSearchConcept.class));
    }

    @Override
    @RequestMapping(path = "/user/posts", method = RequestMethod.POST)
    public Response posts(@RequestBody UserSearchContract userSearch) {
        PostSearchConcept posts = userSearch.posts();
        return Response.of(posts, MethodResolver.availableMethodsOf(PostSearchConcept.class));
    }

    @Override
    @RequestMapping(path = "/user/groups", method = RequestMethod.POST)
    public Response groups(@RequestBody UserSearchContract userSearch) {
        GroupSearchConcept groups = userSearch.groups();
        return Response.of(groups, MethodResolver.availableMethodsOf(GroupSearchConcept.class));
    }


    @Override
    @RequestMapping(path = "/post/likes", method = RequestMethod.POST)
    public Response likes(@RequestBody PostSearchContract postSearch) {
        UserSearchConcept likes = postSearch.likes();
        return Response.of(likes, MethodResolver.availableMethodsOf(UserSearchConcept.class));
    }

    @Override
    @RequestMapping(path = "/post/shares", method = RequestMethod.POST)
    public Response shares(@RequestBody PostSearchContract postSearch) {
        UserSearchConcept shares = postSearch.shares();
        return Response.of(shares, MethodResolver.availableMethodsOf(UserSearchConcept.class));
    }


    @Override
    @RequestMapping(path = "/group/subscribers", method = RequestMethod.POST)
    public Response subscribers(@RequestBody GroupSearchContract groupSearch) {
        UserSearchConcept subscribers = groupSearch.subscribers();
        return Response.of(subscribers, MethodResolver.availableMethodsOf(UserSearchConcept.class));
    }

    @Override
    @RequestMapping(path = "/group/posts", method = RequestMethod.POST)
    public Response posts(@RequestBody GroupSearchContract groupSearch) {
        PostSearchConcept posts = groupSearch.posts();
        return Response.of(posts, MethodResolver.availableMethodsOf(PostSearchConcept.class));
    }


    @Override
    @RequestMapping(path = "/user/where", method = RequestMethod.POST)
    public Response where(@RequestBody UserSearchConcept userSearchConcept /* Condition condition */) {
        UserSearchConcept where = userSearchConcept.where(null);
        return Response.of(where, MethodResolver.availableMethodsOf(UserSearchConcept.class));
    }

    @Override
    @RequestMapping(path = "/post/where", method = RequestMethod.POST)
    public Response where(@RequestBody PostSearchConcept postSearchConcept /* Condition condition */) {
        PostSearchConcept where = postSearchConcept.where(null);
        return Response.of(where, MethodResolver.availableMethodsOf(PostSearchConcept.class));
    }

    @Override
    @RequestMapping(path = "/group/where", method = RequestMethod.POST)
    public Response where(@RequestBody GroupSearchConcept groupSearchConcept /* Condition condition */) {
        GroupSearchConcept where = groupSearchConcept.where(null);
        return Response.of(where, MethodResolver.availableMethodsOf(GroupSearchConcept.class));
    }


    @Override
    @RequestMapping(path = "/user/then", method = RequestMethod.POST)
    public Response then(@RequestBody UserSearchConcept userSearchConcept) {
        UserSearchContract then = userSearchConcept.then();
        return Response.of(then, MethodResolver.availableMethodsOf(UserSearchContract.class));
    }

    @Override
    @RequestMapping(path = "/post/then", method = RequestMethod.POST)
    public Response then(@RequestBody PostSearchConcept postSearchConcept) {
        PostSearchContract then = postSearchConcept.then();
        return Response.of(then, MethodResolver.availableMethodsOf(PostSearchContract.class));
    }

    @Override
    @RequestMapping(path = "/group/then", method = RequestMethod.POST)
    public Response then(@RequestBody GroupSearchConcept groupSearchConcept) {
        GroupSearchContract then = groupSearchConcept.then();
        return Response.of(then, MethodResolver.availableMethodsOf(GroupSearchContract.class));
    }


    @Override
    @RequestMapping(path = "/user/perform", method = RequestMethod.POST)
    public PerformResponse perform(@RequestBody GeneralUserSearch userSearch, @RequestParam(value = "type", required = false, defaultValue = "none") String type) {
        List<User> users = userSearch.perform();

        SerializeView serializer = serializeResolver.resolve(type);
        serializer.serialize(users, "");

        return PerformResponse.of(users);
    }

    @Override
    @RequestMapping(path = "/post/perform", method = RequestMethod.POST)
    public PerformResponse perform(@RequestBody GeneralPostSearch postSearch, @RequestParam(value = "type", required = false, defaultValue = "none") String type) {
        List<Post> posts = postSearch.perform();

        SerializeView serializer = serializeResolver.resolve(type);
        serializer.serialize(posts, "");

        return PerformResponse.of(posts);
    }

    @Override
    @RequestMapping(path = "/group/perform", method = RequestMethod.POST)
    public PerformResponse perform(@RequestBody GeneralGroupSearch groupSearch, @RequestParam(value = "type", required = false, defaultValue = "none") String type) {
        List<Group> groups = groupSearch.perform();

        SerializeView serializer = serializeResolver.resolve(type);
        serializer.serialize(groups, "");

        return PerformResponse.of(groups);
    }
}
