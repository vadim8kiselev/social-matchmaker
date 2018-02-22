package com.kiselev.matchmaker.view.serialize.implementation.self;

import com.kiselev.matchmaker.api.model.Entity;
import com.kiselev.matchmaker.view.serialize.SerializeView;

import java.util.List;

public class DefaultSerializeView implements SerializeView {

    @Override
    public <Pojo extends Entity> void serialize(List<Pojo> entities, String filePath) {
        // Skipped
    }
}
