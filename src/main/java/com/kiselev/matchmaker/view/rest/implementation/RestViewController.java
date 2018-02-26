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
import com.kiselev.matchmaker.view.rest.model.SearchResponse;
import com.kiselev.matchmaker.view.rest.resolver.MethodResolver;
import com.kiselev.matchmaker.view.serialize.SerializeView;
import com.kiselev.matchmaker.view.serialize.resolver.SerializeResolver;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.util.List;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
@RestController
@RequestMapping("/api")
public class RestViewController {

    @Autowired
    private Search search;

    @Autowired
    private SerializeResolver serializeResolver;

    @RequestMapping(path = "/start", method = RequestMethod.GET)
    public SearchResponse start() {
        return SearchResponse.of(null, MethodResolver.availableMethodsOf(Search.class));
    }

    @RequestMapping(path = "/fromUser", method = RequestMethod.GET)
    public SearchResponse fromUser(@RequestParam("userId") String userId) {
        GeneralUserSearch userSearch = search.fromUser(userId);
        return SearchResponse.of(userSearch, MethodResolver.availableMethodsOf(GeneralUserSearch.class));
    }

    @RequestMapping(path = "/fromUsers", method = RequestMethod.GET)
    public SearchResponse fromUsers(@RequestParam("usersIds") List<String> usersIds) {
        GeneralUserSearch userSearch = search.fromUsers(usersIds);
        return SearchResponse.of(userSearch, MethodResolver.availableMethodsOf(GeneralUserSearch.class));
    }

    @RequestMapping(path = "/fromPost", method = RequestMethod.GET)
    public SearchResponse fromPost(@RequestParam("postId") String postId) {
        GeneralPostSearch postSearch = search.fromPost(postId);
        return SearchResponse.of(postSearch, MethodResolver.availableMethodsOf(GeneralPostSearch.class));
    }

    @RequestMapping(path = "/fromPosts", method = RequestMethod.GET)
    public SearchResponse fromPosts(@RequestParam("postsIds") List<String> postsIds) {
        GeneralPostSearch postSearch = search.fromPosts(postsIds);
        return SearchResponse.of(postSearch, MethodResolver.availableMethodsOf(GeneralPostSearch.class));
    }

    @RequestMapping(path = "/fromGroup", method = RequestMethod.GET)
    public SearchResponse fromGroup(@RequestParam("groupId") String groupId) {
        GeneralGroupSearch groupSearch = search.fromGroup(groupId);
        return SearchResponse.of(groupSearch, MethodResolver.availableMethodsOf(GeneralGroupSearch.class));
    }

    @RequestMapping(path = "/fromGroups", method = RequestMethod.GET)
    public SearchResponse fromGroups(@RequestParam("groupsIds") List<String> groupsIds) {
        GeneralGroupSearch groupSearch = search.fromGroups(groupsIds);
        return SearchResponse.of(groupSearch, MethodResolver.availableMethodsOf(GeneralGroupSearch.class));
    }

    @RequestMapping(path = "/user/friends", method = RequestMethod.POST)
    public SearchResponse friends(@RequestBody UserSearchContract userSearch) {
        UserSearchConcept friends = userSearch.friends();
        return SearchResponse.of(friends, MethodResolver.availableMethodsOf(UserSearchConcept.class));
    }

    @RequestMapping(path = "/user/followers", method = RequestMethod.POST)
    public SearchResponse followers(@RequestBody UserSearchContract userSearch) {
        UserSearchConcept followers = userSearch.followers();
        return SearchResponse.of(followers, MethodResolver.availableMethodsOf(UserSearchConcept.class));
    }

    @RequestMapping(path = "/user/subscriptions", method = RequestMethod.POST)
    public SearchResponse subscriptions(@RequestBody UserSearchContract userSearch) {
        UserSearchConcept subscriptions = userSearch.subscriptions();
        return SearchResponse.of(subscriptions, MethodResolver.availableMethodsOf(UserSearchConcept.class));
    }

    @RequestMapping(path = "/user/posts", method = RequestMethod.POST)
    public SearchResponse posts(@RequestBody UserSearchContract userSearch) {
        PostSearchConcept posts = userSearch.posts();
        return SearchResponse.of(posts, MethodResolver.availableMethodsOf(PostSearchConcept.class));
    }

    @RequestMapping(path = "/user/groups", method = RequestMethod.POST)
    public SearchResponse groups(@RequestBody UserSearchContract userSearch) {
        GroupSearchConcept groups = userSearch.groups();
        return SearchResponse.of(groups, MethodResolver.availableMethodsOf(GroupSearchConcept.class));
    }

    @RequestMapping(path = "/post/likes", method = RequestMethod.POST)
    public SearchResponse likes(@RequestBody PostSearchContract postSearch) {
        UserSearchConcept likes = postSearch.likes();
        return SearchResponse.of(likes, MethodResolver.availableMethodsOf(UserSearchConcept.class));
    }

    @RequestMapping(path = "/post/shares", method = RequestMethod.POST)
    public SearchResponse shares(@RequestBody PostSearchContract postSearch) {
        UserSearchConcept shares = postSearch.shares();
        return SearchResponse.of(shares, MethodResolver.availableMethodsOf(UserSearchConcept.class));
    }

    @RequestMapping(path = "/group/subscribers", method = RequestMethod.POST)
    public SearchResponse subscribers(@RequestBody GroupSearchContract groupSearch) {
        UserSearchConcept subscribers = groupSearch.subscribers();
        return SearchResponse.of(subscribers, MethodResolver.availableMethodsOf(UserSearchConcept.class));
    }

    @RequestMapping(path = "/group/posts", method = RequestMethod.POST)
    public SearchResponse posts(@RequestBody GroupSearchContract groupSearch) {
        PostSearchConcept posts = groupSearch.posts();
        return SearchResponse.of(posts, MethodResolver.availableMethodsOf(PostSearchConcept.class));
    }

    @RequestMapping(path = "/user/where", method = RequestMethod.POST)
    public SearchResponse where(@RequestBody UserSearchConcept userSearchConcept /* Condition condition */) {
        UserSearchConcept where = userSearchConcept.where(null);
        return SearchResponse.of(where, MethodResolver.availableMethodsOf(UserSearchConcept.class));
    }

    @RequestMapping(path = "/post/where", method = RequestMethod.POST)
    public SearchResponse where(@RequestBody PostSearchConcept postSearchConcept /* Condition condition */) {
        PostSearchConcept where = postSearchConcept.where(null);
        return SearchResponse.of(where, MethodResolver.availableMethodsOf(PostSearchConcept.class));
    }

    @RequestMapping(path = "/group/where", method = RequestMethod.POST)
    public SearchResponse where(@RequestBody GroupSearchConcept groupSearchConcept /* Condition condition */) {
        GroupSearchConcept where = groupSearchConcept.where(null);
        return SearchResponse.of(where, MethodResolver.availableMethodsOf(GroupSearchConcept.class));
    }

    @RequestMapping(path = "/user/then", method = RequestMethod.POST)
    public SearchResponse then(@RequestBody UserSearchConcept userSearchConcept) {
        UserSearchContract then = userSearchConcept.then();
        return SearchResponse.of(then, MethodResolver.availableMethodsOf(UserSearchContract.class));
    }

    @RequestMapping(path = "/post/then", method = RequestMethod.POST)
    public SearchResponse then(@RequestBody PostSearchConcept postSearchConcept) {
        PostSearchContract then = postSearchConcept.then();
        return SearchResponse.of(then, MethodResolver.availableMethodsOf(PostSearchContract.class));
    }

    @RequestMapping(path = "/group/then", method = RequestMethod.POST)
    public SearchResponse then(@RequestBody GroupSearchConcept groupSearchConcept) {
        GroupSearchContract then = groupSearchConcept.then();
        return SearchResponse.of(then, MethodResolver.availableMethodsOf(GroupSearchContract.class));
    }

    @RequestMapping(path = "/user/perform", method = RequestMethod.POST)
    @Produces({MediaType.APPLICATION_OCTET_STREAM, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void perform(@RequestBody GeneralUserSearch userSearch,
                        @RequestParam(value = "type", defaultValue = "json") String type,
                        HttpServletResponse response) throws IOException {
        List<User> users = userSearch.perform();

        SerializeView serializer = serializeResolver.resolve(type);
        File file = serializer.serialize(users);
        response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());

        IOUtils.copy(new FileInputStream(file), response.getOutputStream());
        response.flushBuffer();
    }

    @RequestMapping(path = "/post/perform", method = RequestMethod.POST)
    @Produces({MediaType.APPLICATION_OCTET_STREAM, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void perform(@RequestBody GeneralPostSearch postSearch,
                        @RequestParam(value = "type", defaultValue = "json") String type,
                        HttpServletResponse response) throws IOException {
        List<Post> posts = postSearch.perform();

        SerializeView serializer = serializeResolver.resolve(type);
        File file = serializer.serialize(posts);
        response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());

        IOUtils.copy(new FileInputStream(file), response.getOutputStream());
        response.flushBuffer();
    }

    @RequestMapping(path = "/group/perform", method = RequestMethod.POST)
    @Produces({MediaType.APPLICATION_OCTET_STREAM, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void perform(@RequestBody GeneralGroupSearch groupSearch,
                        @RequestParam(value = "type", defaultValue = "json") String type,
                        HttpServletResponse response) throws IOException {
        List<Group> groups = groupSearch.perform();

        SerializeView serializer = serializeResolver.resolve(type);
        File file = serializer.serialize(groups);
        response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());

        IOUtils.copy(new FileInputStream(file), response.getOutputStream());
        response.flushBuffer();
    }
}
