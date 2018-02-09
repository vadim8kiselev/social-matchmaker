package com.kiselev.matchmaker.api;

import com.kiselev.matchmaker.api.model.Community;
import com.kiselev.matchmaker.api.model.Post;
import com.kiselev.matchmaker.api.model.User;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
public interface EntityConverter<ExternalUser, ExternalPost, ExternalCommunity> {

    User convertUser(ExternalUser externalEntity);

    Post convertPost(ExternalPost externalPost);

    Community convertGroup(ExternalCommunity externalCommunity);
}
