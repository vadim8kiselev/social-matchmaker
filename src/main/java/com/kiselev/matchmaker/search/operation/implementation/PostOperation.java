package com.kiselev.matchmaker.search.operation.implementation;

import com.kiselev.matchmaker.api.model.Post;
import com.kiselev.matchmaker.search.condition.Condition;
import com.kiselev.matchmaker.search.condition.applier.implementation.PostConditionApplier;
import com.kiselev.matchmaker.search.operation.FilterableOperation;
import com.kiselev.matchmaker.search.operation.PerformableOperation;
import com.kiselev.matchmaker.search.operation.RepeatableOperation;
import com.kiselev.matchmaker.search.operation.StatefulOperation;
import com.kiselev.matchmaker.search.service.implementation.PostSearch;
import com.kiselev.matchmaker.search.service.state.LoopFabric;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
public class PostOperation implements StatefulOperation<Post>, FilterableOperation, RepeatableOperation<PostSearch>, PerformableOperation<Post> {

    @Autowired
    private LoopFabric loopFabric;

    @Autowired
    private PostConditionApplier postConditionApplier;

    private List<Post> posts;

    @Override
    public PostOperation from(List<Post> posts) {
        this.posts = posts;
        return this;
    }

    public PostOperation where(Condition condition) {
        return from(postConditionApplier.apply(posts, condition));
    }

    public PostSearch then() {
        return loopFabric.createSearch().fromPosts(posts.stream()
                .map(Post::getId)
                .collect(Collectors.toList()));
    }

    @Override
    public List<Post> perform() {
        return posts;
    }
}
