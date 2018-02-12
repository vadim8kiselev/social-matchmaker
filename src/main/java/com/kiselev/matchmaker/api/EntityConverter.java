package com.kiselev.matchmaker.api;

import com.kiselev.matchmaker.api.model.Group;
import com.kiselev.matchmaker.api.model.Post;
import com.kiselev.matchmaker.api.model.User;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
public interface EntityConverter<ExternalUser, ExternalPost, ExternalGroup> {

    User convertUser(ExternalUser externalEntity);

    Post convertPost(ExternalPost externalPost);

    Group convertGroup(ExternalGroup externalGroup);
}
