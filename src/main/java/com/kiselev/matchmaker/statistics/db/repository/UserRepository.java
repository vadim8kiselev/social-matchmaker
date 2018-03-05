package com.kiselev.matchmaker.statistics.db.repository;

import com.kiselev.matchmaker.api.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
}
