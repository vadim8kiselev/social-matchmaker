package com.kiselev.matchmaker.search.condition.matcher;

import com.kiselev.matchmaker.api.model.Mapper;
import com.kiselev.matchmaker.api.model.entity.Group;
import com.kiselev.matchmaker.api.model.entity.Post;
import com.kiselev.matchmaker.api.model.entity.User;
import com.kiselev.matchmaker.api.model.mapper.GroupMapper;
import com.kiselev.matchmaker.api.model.mapper.PostMapper;
import com.kiselev.matchmaker.api.model.mapper.UserMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConditionMatcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConditionMatcher.class);

    @PostConstruct
    public void matchConditions() {
        LOGGER.info("Start matching equal entities fields with mappers keys");
        matchUserConditions();
        matchPostConditions();
        matchGroupConditions();
        LOGGER.info("End matching equal entities fields with mappers keys");
    }

    private void matchUserConditions() {
        List<String> actualFields = getFieldsNamesOf(User.class);
        List<String> declaredFields = getDeclaredFieldsNamesFromMapper(UserMapper.values());

        if (areCollectionsNotEqual(actualFields, declaredFields)) {
            throw new RuntimeException("User fields is not consistent with User mapper's keys");
        }
    }

    private void matchPostConditions() {
        List<String> actualFields = getFieldsNamesOf(Post.class);
        List<String> declaredFields = getDeclaredFieldsNamesFromMapper(PostMapper.values());

        if (areCollectionsNotEqual(actualFields, declaredFields)) {
            throw new RuntimeException("Post fields is not consistent with Post mapper's keys");
        }
    }

    private void matchGroupConditions() {
        List<String> actualFields = getFieldsNamesOf(Group.class);
        List<String> declaredFields = getDeclaredFieldsNamesFromMapper(GroupMapper.values());

        if (areCollectionsNotEqual(actualFields, declaredFields)) {
            throw new RuntimeException("Group fields is not consistent with Group mapper's keys");
        }
    }

    private List<String> getFieldsNamesOf(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .map(Field::getName)
                .collect(Collectors.toList());
    }

    private List<String> getDeclaredFieldsNamesFromMapper(Mapper[] values) {
        return Arrays.stream(values)
                .map(Mapper::field)
                .collect(Collectors.toList());
    }

    private boolean areCollectionsNotEqual(List<String> collection1, List<String> collection2) {
        return !CollectionUtils.isEqualCollection(collection1, collection2);
    }
}
