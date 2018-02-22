package com.kiselev.matchmaker.view.rest.resolver;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kiselev.matchmaker.search.service.Search;
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
        colors.put("fromUser", "#efefef");
        colors.put("fromUsers", "#efefef");
        colors.put("fromPost", "#efefef");
        colors.put("fromPosts", "#efefef");
        colors.put("fromGroup", "#efefef");
        colors.put("fromGroups", "#efefef");


        colors.put("friends", "#4779BE");
        colors.put("followers", "#4779BE");
        colors.put("subscriptions", "#4779BE");
        colors.put("likes", "#4779BE");
        colors.put("shares", "#4779BE");
        colors.put("subscribers", "#4779BE");

        colors.put("posts", "#841F27");

        colors.put("groups", "#fffacd");

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

        prettifyMethodsNames.put("from", null);
        prettifyMethodsNames.put("fromEntities", null);

        prettifyMethodsNames.put("where", "Where");
        prettifyMethodsNames.put("then", "Then");
        prettifyMethodsNames.put("perform", "Perform");
    }

    public static List<AvailableMethod> availableMethodsOf(Class clazz) {
        List<AvailableMethod> methods = Lists.newArrayList();

        for (Method method : ReflectionUtils.getAllDeclaredMethods(clazz)) {
            String name = getName(method);
            if (name != null) {
                String type = getType(method);
                String parameter = getParameter(method);
                String uri = getUri(method);
                String color = getColor(method);
                methods.add(AvailableMethod.of(name, type, uri, parameter, color));
            }
        }

        return methods;
    }

    private static String getName(Method method) {
        return prettifyMethodsNames.get(method.getName());
    }

    private static String getType(Method method) {
        return method.getDeclaringClass().isAssignableFrom(Search.class) ? "GET" : "POST";
    }

    private static String getParameter(Method method) {
        List<String> parameters = Arrays.stream(method.getParameters())
                .filter(parameter -> parameter.getType().isAssignableFrom(String.class) || parameter.getType().isAssignableFrom(List.class))
                .map(Parameter::getName)
                .collect(Collectors.toList());

        return String.join(",", parameters);
    }

    private static String getUri(Method method) {
        String uri = endpoints.get(method.getName());
        if (uri == null) {
            uri = additionalResolve(method);
        }
        return uri;
    }

    private static String additionalResolve(Method method) {
        Class<?> clazz = method.getDeclaringClass();

        if (clazz.isAssignableFrom(UserSearch.class)) {
            return "api/user/" + method.getName();
        } else if (clazz.isAssignableFrom(PostSearch.class)) {
            return "api/post/" + method.getName();
        } else if (clazz.isAssignableFrom(GroupSearch.class)) {
            return "api/group/" + method.getName();
        }
        throw new IllegalArgumentException("Illegal type of method");
    }

    private static String getColor(Method method) {
        return colors.get(method.getName());
    }
}
