package com.kiselev.matchmaker.search.condition.implementation;

import com.kiselev.matchmaker.api.model.Entity;
import com.kiselev.matchmaker.api.model.Mapper;
import com.kiselev.matchmaker.search.condition.SearchCondition;

import java.lang.reflect.Field;

public class EqualCondition implements SearchCondition {

    private String field;

    private String value;

    private boolean contains;

    private EqualCondition(String field, String value, boolean contains) {
        this.field = field;
        this.value = value;
        this.contains = contains;
    }

    public static EqualCondition equal(Mapper constant, String value) {
        return new EqualCondition(constant.field(), value, false);
    }

    public static EqualCondition contains(Mapper constant, String value) {
        return new EqualCondition(constant.field(), value, true);
    }

    @Override
    public boolean isCompleted(Entity entity) {
        Class<?> clazz = entity.getClass();
        try {
            Field field = clazz.getDeclaredField(this.field);
            field.setAccessible(true);
            String expected = field.get(entity).toString();
            return value != null && expected != null
                    && (contains ? contains(value, expected) : equal(value, expected));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean equal(String actual, String expected) {
        return actual.equals(expected); // TODO: equalsIgnoreCase
    }

    private boolean contains(String actual, String expected) {
        return actual.contains(expected); // TODO: .toLowerCase()
    }
}
