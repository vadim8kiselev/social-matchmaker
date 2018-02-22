package com.kiselev.matchmaker.view.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableMethod {

    private String name;

    private String type;

    private String uri;

    private String parameter;

    private String color;

    public static AvailableMethod of(String name, String type, String uri, String parameter, String color) {
        return new AvailableMethod(name, type, uri, parameter, color);
    }
}
