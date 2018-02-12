package com.kiselev.matchmaker.search.condition.applier.implementation;

import com.kiselev.matchmaker.api.model.Post;
import com.kiselev.matchmaker.search.condition.Condition;
import com.kiselev.matchmaker.search.condition.applier.ConditionApplier;

import java.util.List;

public class PostConditionApplier implements ConditionApplier<Post> {

    @Override
    public List<Post> apply(List<Post> posts, Condition condition) {
        // TODO: filter
        return posts;
    }
}
