package com.kiselev.matchmaker.view.rest.resolver;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kiselev.matchmaker.search.service.Search;
import com.kiselev.matchmaker.search.service.concept.theory.PerformableSearch;
import com.kiselev.matchmaker.search.service.target.implementation.GroupSearch;
import com.kiselev.matchmaker.search.service.target.implementation.PostSearch;
import com.kiselev.matchmaker.search.service.target.implementation.UserSearch;
import com.kiselev.matchmaker.view.rest.model.AvailableMethod;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MethodResolver {

    private static final Map<String, String> colors = Maps.newHashMap();

    private static final Map<String, String> endpoints = Maps.newHashMap();

    private static final Map<String, String> prettifyMethodsNames = Maps.newHashMap();

    static {
        colors.put("fromUser", "#a0b0d0");
        colors.put("fromUsers", "#a0b0d0");
        colors.put("fromPost", "#d0a0b0");
        colors.put("fromPosts", "#d0a0b0");
        colors.put("fromGroup", "#a0d0b0");
        colors.put("fromGroups", "#a0d0b0");


        colors.put("friends", "#a0b0d0");
        colors.put("followers", "#a0b0d0");
        colors.put("subscriptions", "#a0b0d0");
        colors.put("likes", "#a0b0d0");
        colors.put("shares", "#a0b0d0");
        colors.put("subscribers", "#a0b0d0");

        colors.put("posts", "#d0a0b0");

        colors.put("groups", "#a0d0b0");

        colors.put("with", "#efefef");
        colors.put("where", "#efefef");
        colors.put("then", "#efefef");
        colors.put("perform", "#efefef");
    }

    static {
        endpoints.put("fromUser", "api/fromUser");
        endpoints.put("fromUsers", "api/fromUsers");

        endpoints.put("fromPost", "api/fromPost");
        endpoints.put("fromPosts", "api/fromPosts");

        endpoints.put("fromGroup", "api/fromGroup");
        endpoints.put("fromGroups", "api/fromGroups");


        endpoints.put("friends", "api/user/friends");
        endpoints.put("followers", "api/user/followers");
        endpoints.put("subscriptions", "api/user/subscriptions");
        endpoints.put("groups", "api/user/groups");

        endpoints.put("likes", "api/post/likes");
        endpoints.put("shares", "api/post/shares");

        endpoints.put("subscribers", "api/group/subscribers");
    }

    static {
        prettifyMethodsNames.put("fromUser", "From user");
        prettifyMethodsNames.put("fromUsers", "From users");

        prettifyMethodsNames.put("fromPost", "From post");
        prettifyMethodsNames.put("fromPosts", "From posts");

        prettifyMethodsNames.put("fromGroup", "From group");
        prettifyMethodsNames.put("fromGroups", "From groups");


        prettifyMethodsNames.put("friends", "Friends");
        prettifyMethodsNames.put("followers", "Followers");
        prettifyMethodsNames.put("subscriptions", "Subscriptions");
        prettifyMethodsNames.put("posts", "Posts");
        prettifyMethodsNames.put("groups", "Groups");

        prettifyMethodsNames.put("likes", "Likes");
        prettifyMethodsNames.put("shares", "Shares");

        prettifyMethodsNames.put("subscribers", "Subscribers");


        prettifyMethodsNames.put("with", "With");
        prettifyMethodsNames.put("where", "Where");
        prettifyMethodsNames.put("then", "Then");
        prettifyMethodsNames.put("perform", "Perform");
    }

    public static List<AvailableMethod> availableMethodsOf(Class<?> clazz) {
        List<AvailableMethod> methods = Lists.newArrayList();

        for (Method method : ReflectionUtils.getAllDeclaredMethods(clazz)) {
            String name = getName(method);
            if (name != null) {
                String type = getType(clazz);
                String parameter = getParameter(method);
                String uri = getUri(clazz, method);
                String color = getColor(method);
                methods.add(AvailableMethod.of(name, type, uri, parameter, color));
            }
        }

        return methods;
    }

    private static String getName(Method method) {
        return prettifyMethodsNames.get(method.getName());
    }

    private static String getType(Class<?> clazz) {
        return clazz.isAssignableFrom(Search.class) ? "GET" : "POST";
    }

    private static String getParameter(Method method) {
        List<String> parameters = Arrays.stream(method.getParameters())
                .filter(parameter -> parameter.getType().isAssignableFrom(String.class) || parameter.getType().isAssignableFrom(List.class))
                .map(Parameter::getName)
                .collect(Collectors.toList());

        if (method.getDeclaringClass().isAssignableFrom(PerformableSearch.class)) {
            parameters.add("type");
        }

        return String.join(",", parameters);
    }

    private static String getUri(Class<?> clazz, Method method) {
        String uri = endpoints.get(method.getName());
        if (uri == null) {
            uri = additionalResolve(clazz, method);
        }
        return uri;
    }

    private static String additionalResolve(Class<?> clazz, Method method) {
        String methodName = method.getName();

        if (clazz.isAssignableFrom(UserSearch.class)) {
            return "api/user/" + methodName;
        } else if (clazz.isAssignableFrom(PostSearch.class)) {
            return "api/post/" + methodName;
        } else if (clazz.isAssignableFrom(GroupSearch.class)) {
            return "api/group/" + methodName;
        }
        throw new IllegalArgumentException("Illegal type of method");
    }

    private static String getColor(Method method) {
        return colors.get(method.getName());
    }
}
