package com.kiselev.matchmaker.search.condition;

public interface Condition<Pojo> {

    boolean isCompleted(Pojo pojo);
}
