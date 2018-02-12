package com.kiselev.matchmaker.api.implementation.facebook;

import com.kiselev.matchmaker.api.EntityConverter;
import com.kiselev.matchmaker.api.model.Group;
import com.kiselev.matchmaker.api.model.Post;
import com.kiselev.matchmaker.api.model.User;

/**
 * @author: Vadim Kiselev
 * @date: 12.02.2018
 */
public class FacebookEntityConverter<ExternalUser, ExternalWallPost, ExternalGroup>
        implements EntityConverter<ExternalUser, ExternalWallPost, ExternalGroup> {

    @Override
    public User convertUser(ExternalUser externalEntity) {
        return User.builder()
                .build();
    }

    @Override
    public Post convertPost(ExternalWallPost externalPost) {
        return Post.builder()
                .build();
    }

    @Override
    public Group convertGroup(ExternalGroup externalGroup) {
        return Group.builder()
                .build();
    }
}
