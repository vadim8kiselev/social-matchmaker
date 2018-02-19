package com.kiselev.matchmaker.api;

import com.kiselev.matchmaker.api.model.entity.Group;
import com.kiselev.matchmaker.api.model.entity.Post;
import com.kiselev.matchmaker.api.model.entity.User;

import java.util.List;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
public interface EntityConverter<ExternalUser, ExternalPost, ExternalGroup> {

    User convertUser(ExternalUser externalEntity);

    List<User> convertUsers(List<ExternalUser> externalEntities);

    Post convertPost(ExternalPost externalPost);

    List<Post> convertPosts(List<ExternalPost> externalPosts);

    Group convertGroup(ExternalGroup externalGroup);

    List<Group> convertGroups(List<ExternalGroup> externalGroups);
}
