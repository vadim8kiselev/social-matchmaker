package com.kiselev.matchmaker.api.model;

import lombok.Builder;
import lombok.Getter;

/**
 * @author: Vadim Kiselev
 * @date: 09.02.2018
 */
@Builder
@Getter
public class Community {
    private String id;
    private String name;
    private String status;
    private String numberOfSubscribers;
}
