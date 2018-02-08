package com.kiselev.matchmaker.api.implementation.vk;

import com.kiselev.matchmaker.api.EntityConverter;
import com.kiselev.matchmaker.api.model.User;
import com.vk.api.sdk.objects.friends.UserXtrLists;
import org.springframework.stereotype.Component;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
@Component
public class VKEntityConverter implements EntityConverter<UserXtrLists> {

    @Override
    public User convertUser(UserXtrLists externalEntity) {
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

    private String transform(String value) {
        return value != null ? value.trim().toLowerCase() : "";
    }
}
