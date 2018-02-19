package com.kiselev.matchmaker.api.model.entity;

import com.kiselev.matchmaker.api.model.Entity;
import lombok.Builder;
import lombok.Getter;

/**
 * @author: Vadim Kiselev
 * @date: 09.02.2018
 */
@Builder
@Getter
public class Post implements Entity {
    private String id;
    private String fromId;
    private String ownerId;
    private String text;
    private String date;

    private String likesCount;
    private String sharesCount;
    private String viewsCount;
    private String commentsCount;

    private String geo;
    private String attachments;

    private String type;
    private String platform;
}
