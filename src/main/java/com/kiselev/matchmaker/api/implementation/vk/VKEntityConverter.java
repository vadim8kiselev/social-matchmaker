package com.kiselev.matchmaker.api.implementation.vk;

import com.kiselev.matchmaker.api.EntityConverter;
import com.kiselev.matchmaker.api.model.Community;
import com.kiselev.matchmaker.api.model.Post;
import com.kiselev.matchmaker.api.model.User;
import com.vk.api.sdk.objects.groups.GroupFull;
import com.vk.api.sdk.objects.users.UserFull;
import com.vk.api.sdk.objects.wall.WallpostFull;
import org.springframework.stereotype.Component;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
@Component
public class VKEntityConverter implements EntityConverter<UserFull, WallpostFull, GroupFull> {

    @Override
    public User convertUser(UserFull externalEntity) {
        return User.builder()
                .id(externalEntity.getId().toString())
                .firstName(externalEntity.getFirstName())
                .lastName(externalEntity.getLastName())
                .birthday(externalEntity.getBdate())
                .city(externalEntity.getCity() != null ? externalEntity.getCity().getTitle() : "")
                .country(externalEntity.getCountry() != null ? externalEntity.getCountry().getTitle() : "")
                .mobilePhone(transform(externalEntity.getMobilePhone()))
                .homePhone(transform(externalEntity.getHomePhone()))
                .online(externalEntity.isOnline())
                .sex(externalEntity.getSex() != null ? transform(externalEntity.getSex().name()) : "")
                .build();
    }

    @Override
    public Post convertPost(WallpostFull externalPost) {
        return Post.builder()
                .id(externalPost.getId().toString())
                .fromId(externalPost.getFromId().toString())
                .ownerId(externalPost.getOwnerId().toString())
                .date(externalPost.getDate().toString())
                .text(externalPost.getText())
                .build();
    }

    @Override
    public Community convertGroup(GroupFull externalCommunity) {
        return Community.builder()
                .id(externalCommunity.getId())
                .name(externalCommunity.getName())
                .status(externalCommunity.getStatus())
                .numberOfSubscribers(externalCommunity.getMembersCount() != null ? externalCommunity.getMembersCount().toString() : "")
                .build();
    }

    private String transform(String value) {
        return value != null ? value.trim().toLowerCase() : "";
    }
}
