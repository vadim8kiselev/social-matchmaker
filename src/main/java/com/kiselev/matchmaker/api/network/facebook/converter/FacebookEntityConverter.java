package com.kiselev.matchmaker.api.network.facebook.converter;

import com.kiselev.matchmaker.api.EntityConverter;
import com.kiselev.matchmaker.api.model.entity.Group;
import com.kiselev.matchmaker.api.model.entity.Post;
import com.kiselev.matchmaker.api.model.entity.User;

import java.util.List;

/**
 * @author: Vadim Kiselev
 * @date: 12.02.2018
 */
public class FacebookEntityConverter<ExternalUser, ExternalWallPost, ExternalGroup>
        implements EntityConverter<ExternalUser, ExternalWallPost, ExternalGroup> {

    @Override
    public User convertUser(ExternalUser externalEntity) {
        throw new UnsupportedOperationException("Facebook API is not implemented yet.");
    }

    @Override
    public List<User> convertUsers(List<ExternalUser> externalEntities) {
        throw new UnsupportedOperationException("Facebook API is not implemented yet.");
    }

    @Override
    public Post convertPost(ExternalWallPost externalPost) {
        throw new UnsupportedOperationException("Facebook API is not implemented yet.");
    }

    @Override
    public List<Post> convertPosts(List<ExternalWallPost> externalWallPosts) {
        throw new UnsupportedOperationException("Facebook API is not implemented yet.");
    }

    @Override
    public Group convertGroup(ExternalGroup externalGroup) {
        throw new UnsupportedOperationException("Facebook API is not implemented yet.");
    }

    @Override
    public List<Group> convertGroups(List<ExternalGroup> externalGroups) {
        throw new UnsupportedOperationException("Facebook API is not implemented yet.");
    }
}
