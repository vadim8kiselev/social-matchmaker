package com.kiselev.matchmaker.api.model.entity;

import lombok.Getter;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * @author: Vadim Kiselev
 * @date: 09.02.2018
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Post extends AbstractEntity {
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
