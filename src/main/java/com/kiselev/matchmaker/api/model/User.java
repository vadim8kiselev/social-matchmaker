package com.kiselev.matchmaker.api.model;


import lombok.Builder;
import lombok.Getter;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
@Builder
@Getter
public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String sex;
    private Boolean online;
    private String birthday;
    private String city;
    private String country;
    private String mobilePhone;
    private String homePhone;
}
