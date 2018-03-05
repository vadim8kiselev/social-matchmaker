package com.kiselev.matchmaker.statistics.db.repository;

import com.kiselev.matchmaker.api.model.entity.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends CrudRepository<Group, String> {
}
