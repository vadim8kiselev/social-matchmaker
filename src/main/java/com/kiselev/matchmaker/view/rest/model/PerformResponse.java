package com.kiselev.matchmaker.view.rest.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.kiselev.matchmaker.api.model.Entity;
import com.kiselev.matchmaker.api.model.entity.Group;
import com.kiselev.matchmaker.api.model.entity.Post;
import com.kiselev.matchmaker.api.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerformResponse<Pojo extends Entity> {

    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
    @JsonSubTypes({
            @JsonSubTypes.Type(value = User.class, name = "UserSearch"),
            @JsonSubTypes.Type(value = Post.class, name = "PostSearch"),
            @JsonSubTypes.Type(value = Group.class, name = "GroupSearch")})
    private List<Pojo> entities;

    public static <Pojo extends Entity> PerformResponse of(List<Pojo> entities) {
        return new PerformResponse<>(entities);
    }
}
