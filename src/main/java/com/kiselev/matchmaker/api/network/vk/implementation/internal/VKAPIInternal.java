package com.kiselev.matchmaker.api.network.vk.implementation.internal;

import com.google.common.collect.Lists;
import com.kiselev.matchmaker.api.EntityConverter;
import com.kiselev.matchmaker.api.model.entity.Group;
import com.kiselev.matchmaker.api.model.entity.Post;
import com.kiselev.matchmaker.api.model.entity.User;
import com.kiselev.matchmaker.api.network.vk.exception.ExceptionHandler;
import com.kiselev.matchmaker.api.network.vk.utils.VKUtils;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.friends.responses.GetFieldsResponse;
import com.vk.api.sdk.objects.groups.GroupFull;
import com.vk.api.sdk.objects.groups.responses.GetMembersResponse;
import com.vk.api.sdk.objects.likes.responses.GetListResponse;
import com.vk.api.sdk.objects.users.UserFull;
import com.vk.api.sdk.objects.users.responses.GetFollowersFieldsResponse;
import com.vk.api.sdk.objects.users.responses.GetSubscriptionsResponse;
import com.vk.api.sdk.objects.wall.WallpostFull;
import com.vk.api.sdk.objects.wall.responses.GetRepostsResponse;
import com.vk.api.sdk.objects.wall.responses.GetResponse;
import com.vk.api.sdk.queries.friends.FriendsGetQueryWithFields;
import com.vk.api.sdk.queries.groups.GroupField;
import com.vk.api.sdk.queries.groups.GroupsGetMembersQuery;
import com.vk.api.sdk.queries.groups.GroupsGetQuery;
import com.vk.api.sdk.queries.likes.LikesGetListQuery;
import com.vk.api.sdk.queries.likes.LikesType;
import com.vk.api.sdk.queries.users.UserField;
import com.vk.api.sdk.queries.users.UsersGetFollowersQueryWithFields;
import com.vk.api.sdk.queries.users.UsersGetSubscriptionsQuery;
import com.vk.api.sdk.queries.wall.WallGetQuery;
import com.vk.api.sdk.queries.wall.WallGetRepostsQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.stream.Collectors;

import static com.kiselev.matchmaker.api.network.vk.constants.VKConstants.MaxCount;

public class VKAPIInternal implements SocialNetworkAPIInternal {

    @Autowired
    private EntityConverter<UserFull, WallpostFull, GroupFull> converter;

    private VkApiClient vk;

    private UserActor user;

    @Override
    public void auth(VkApiClient vk, UserActor user) {
        this.vk = vk;
        this.user = user;
    }

    @Override
    @Cacheable("users")
    public List<User> getUsersByUsersIds(List<String> usersIds) {
        List<List<String>> lists = Lists.partition(usersIds, MaxCount.USERS_GET);
        return lists.stream()
                .map(list -> {
                    try {
                        VKUtils.timeout();
                        return vk.users()
                                .get(user)
                                .fields(UserField.values())
                                .userIds(list)
                                .execute().stream()
                                .map(converter::convertUser)
                                .collect(Collectors.toList());
                    } catch (ApiException | ClientException exception) {
                        ExceptionHandler.handleException(exception);
                    }
                    return Lists.<User>newArrayList();
                })
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable("users")
    public List<User> getFriendsByUserId(String userId) {
        try {
            List<UserFull> friends = Lists.newArrayList();

            FriendsGetQueryWithFields query = vk.friends()
                    .get(user, UserField.values())
                    .userId(Integer.parseInt(userId))
                    .count(MaxCount.FRIENDS_GET);

            GetFieldsResponse execute = query.execute();
            friends.addAll(execute.getItems());

            if (execute.getCount().equals(MaxCount.FRIENDS_GET)) {
                friends.addAll(query
                        .offset(MaxCount.FRIENDS_GET)
                        .execute().getItems());
            }

            return converter.convertUsers(friends);
        } catch (ApiException | ClientException exception) {
            ExceptionHandler.handleException(exception);
        }
        return Lists.newArrayList();
    }

    @Override
    @Cacheable("users")
    public List<User> getFollowersByUserId(String userId) {
        try {
            UsersGetFollowersQueryWithFields query = vk.users()
                    .getFollowers(user, UserField.values())
                    .userId(Integer.parseInt(userId))
                    .count(MaxCount.USERS_GET_FOLLOWERS);

            int offset = 0;
            GetFollowersFieldsResponse response = query.offset(offset).execute();

            List<UserFull> followers = response.getItems();
            while (response.getCount().equals(MaxCount.USERS_GET_FOLLOWERS)) {
                response = query.offset(offset).execute();
                followers.addAll(response.getItems());
                offset += MaxCount.USERS_GET_FOLLOWERS;
            }

            return followers.stream()
                    .map(converter::convertUser)
                    .collect(Collectors.toList());
        } catch (ApiException | ClientException exception) {
            ExceptionHandler.handleException(exception);
        }
        return Lists.newArrayList();
    }

    @Override
    @Cacheable("users")
    public List<User> getSubscriptionsByUserId(String userId) {
        try {
            UsersGetSubscriptionsQuery query = vk.users()
                    .getSubscriptions(user)
                    .userId(Integer.parseInt(userId))
                    .count(MaxCount.USERS_GET_SUBSCRIPTIONS);

            int offset = 0;
            GetSubscriptionsResponse response = query.offset(offset).execute();
            VKUtils.timeout();

            List<Integer> subscriptionsIds = response.getUsers().getItems();
            while (response.getUsers().getCount().equals(MaxCount.USERS_GET_SUBSCRIPTIONS)) {
                response = query.offset(offset).execute();
                VKUtils.timeout();
                subscriptionsIds.addAll(response.getUsers().getItems());
                offset += MaxCount.USERS_GET_SUBSCRIPTIONS;
            }

            return getUsersByUsersIds(VKUtils.toStringList(subscriptionsIds));

        } catch (ApiException | ClientException exception) {
            ExceptionHandler.handleException(exception);
        }
        return Lists.newArrayList();
    }

    @Override
    @Cacheable("posts")
    public List<Post> getPostsByUserId(String userId) {
        return getPostsByOwnerId(userId);
    }

    @Override
    @Cacheable("groups")
    public List<Group> getGroupsByUserId(String userId) {
        try {
            GroupsGetQuery query = vk.groups().get(user)
                    .userId(Integer.parseInt(userId))
                    .fields(GroupField.values())
                    .count(MaxCount.GROUPS_GET);

            int offset = 0;
            com.vk.api.sdk.objects.groups.responses.GetResponse response = query.offset(offset).execute();
            VKUtils.timeout();

            List<Integer> groupsIds = response.getItems();
            while (response.getCount().equals(MaxCount.GROUPS_GET)) {
                response = query.offset(offset).execute();
                VKUtils.timeout();
                groupsIds.addAll(response.getItems());
                offset += MaxCount.GROUPS_GET;
            }

            return getGroupsByGroupsIds(VKUtils.toStringList(groupsIds));
        } catch (ApiException | ClientException exception) {
            ExceptionHandler.handleException(exception);
        }
        return Lists.newArrayList();
    }

    @Override
    @Cacheable("posts")
    public List<Post> getPostsByPostsIds(List<String> postsIds) {
        return Lists.partition(postsIds, MaxCount.WALL_GET_BY_ID).stream()
                .map(posts -> {
                    try {
                        return vk.wall().getById(user, posts)
                                .execute().stream()
                                .map(converter::convertPost)
                                .collect(Collectors.toList());
                    } catch (ApiException | ClientException exception) {
                        ExceptionHandler.handleException(exception);
                    }
                    return Lists.<Post>newArrayList();
                })
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable("users")
    public List<User> getLikesByPostId(String postId) {
        try {
            String[] ownerId_postId = postId.split("_");

            LikesGetListQuery query = vk.likes().getList(user, LikesType.POST)
                    .ownerId(Integer.parseInt(ownerId_postId[0]))
                    .itemId(Integer.parseInt(ownerId_postId[1]))
                    .count(MaxCount.LIKES_GET_LIST);

            int offset = 0;
            GetListResponse response = query.execute();
            VKUtils.timeout();

            List<Integer> likesIds = response.getItems();
            while (response.getCount().equals(MaxCount.LIKES_GET_LIST)) {
                response = query.offset(offset).execute();
                VKUtils.timeout();
                likesIds.addAll(response.getItems());
                offset += MaxCount.LIKES_GET_LIST;
            }

            return getUsersByUsersIds(VKUtils.toStringList(likesIds));
        } catch (ApiException | ClientException exception) {
            ExceptionHandler.handleException(exception);
        }
        return Lists.newArrayList();
    }

    @Override
    @Cacheable("users")
    public List<User> getSharesByPostId(String postId) {
        try {
            String[] ownerId_postId = postId.split("_");

            WallGetRepostsQuery query = vk.wall().getReposts(user)
                    .ownerId(Integer.parseInt(ownerId_postId[0]))
                    .postId(Integer.parseInt(ownerId_postId[1]))
                    .count(MaxCount.WALL_GET_REPOSTS);

            int offset = 0;
            GetRepostsResponse response = query.execute();
            VKUtils.timeout();

            List<Integer> sharersIds = response.getProfiles().stream()
                    .map(com.vk.api.sdk.objects.users.User::getId)
                    .collect(Collectors.toList());

            while (response.getProfiles().size() == MaxCount.WALL_GET_REPOSTS) {
                response = query.offset(offset).execute();
                VKUtils.timeout();
                sharersIds.addAll(response.getProfiles().stream()
                        .map(com.vk.api.sdk.objects.users.User::getId)
                        .collect(Collectors.toList()));
                offset += MaxCount.WALL_GET_REPOSTS;
            }

            return getUsersByUsersIds(VKUtils.toStringList(sharersIds));
        } catch (ApiException | ClientException exception) {
            ExceptionHandler.handleException(exception);
        }
        return Lists.newArrayList();
    }

    @Override
    @Cacheable("groups")
    public List<Group> getGroupsByGroupsIds(List<String> groupsIds) {
        return Lists.partition(groupsIds, MaxCount.GROUPS_GET_BY_ID).stream()
                .map(groups -> {
                    try {
                        VKUtils.timeout();
                        return converter.convertGroups(vk.groups().getById(user)
                                .fields(GroupField.values())
                                .groupIds(groups)
                                .execute());
                    } catch (ApiException | ClientException exception) {
                        ExceptionHandler.handleException(exception);
                    }
                    return Lists.<Group>newArrayList();
                })
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable("users")
    public List<User> getSubscribersByGroupId(String groupId) {
        try {
            GroupsGetMembersQuery query = vk.groups().getMembers(user)
                    .groupId(groupId)
                    .count(MaxCount.GROUPS_GET_MEMBERS);

            int offset = 0;
            GetMembersResponse response = query.offset(offset).execute();
            VKUtils.timeout();

            List<Integer> subscribersIds = response.getItems();
            while (response.getCount().equals(MaxCount.GROUPS_GET_MEMBERS)) {
                response = query.offset(offset).execute();
                VKUtils.timeout();
                subscribersIds.addAll(response.getItems());
                offset += MaxCount.GROUPS_GET_MEMBERS;
            }

            return getUsersByUsersIds(VKUtils.toStringList(subscribersIds));
        } catch (ApiException | ClientException exception) {
            ExceptionHandler.handleException(exception);
        }
        return Lists.newArrayList();
    }

    @Override
    @Cacheable("posts")
    public List<Post> getPostsByGroupId(String groupId) {
        return getPostsByOwnerId(groupId);
    }

    @Override
    @Cacheable("posts")
    public List<Post> getPostsByOwnerId(String ownerId) {
        try {
            WallGetQuery query = vk.wall().get(user)
                    .ownerId(Integer.parseInt(ownerId))
                    .count(MaxCount.WALL_GET);

            int offset = 0;
            GetResponse response = query.offset(offset).execute();
            VKUtils.timeout();

            List<WallpostFull> posts = response.getItems();
            while (response.getCount().equals(MaxCount.WALL_GET)) {
                response = query.offset(offset).execute();
                VKUtils.timeout();
                posts.addAll(response.getItems());
                offset += MaxCount.WALL_GET;
            }

            return converter.convertPosts(posts);
        } catch (ApiException | ClientException exception) {
            ExceptionHandler.handleException(exception);
        }
        return Lists.newArrayList();
    }
}
