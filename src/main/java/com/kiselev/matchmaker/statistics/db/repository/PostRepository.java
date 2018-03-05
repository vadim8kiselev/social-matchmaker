package com.kiselev.matchmaker.statistics.db.repository;

import com.kiselev.matchmaker.api.model.entity.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<Post, String> {
}
