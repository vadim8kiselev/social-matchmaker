package com.kiselev.matchmaker.search.condition;

import com.kiselev.matchmaker.api.model.Entity;
import com.kiselev.matchmaker.search.condition.builder.ConditionBuilder;

/**
 * General interface for hierarchy of conditions, which used for filter entities
 *
 * @see ConditionBuilder for composing of conditions
 */
public interface SearchCondition {

    boolean isCompleted(Entity entity);
}
