package com.kiselev.matchmaker.api.model.entity;

import lombok.Getter;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author: Vadim Kiselev
 * @date: 09.02.2018
 */
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "posts")
@EqualsAndHashCode(callSuper = false)
public class Post extends AbstractEntity {

    @Id
    @Column(name = "post_id")
    private String id;

    @Column(name = "from_id")
    private String fromId;

    @Column(name = "owner_id")
    private String ownerId;

    @Column(name = "text")
    private String text;

    @Column(name = "date")
    private String date;


    @Column(name = "likes_count")
    private String likesCount;

    @Column(name = "shares_count")
    private String sharesCount;

    @Column(name = "views_count")
    private String viewsCount;

    @Column(name = "comments_count")
    private String commentsCount;


    @Column(name = "geo")
    private String geo;

    @Column(name = "attachments")
    private String attachments;


    @Column(name = "type")
    private String type;

    @Column(name = "platform")
    private String platform;
}
