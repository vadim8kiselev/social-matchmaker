package com.kiselev.matchmaker.view.serialize;

import com.kiselev.matchmaker.api.model.Entity;

import java.io.File;
import java.util.List;

public interface SerializeView {

    <Pojo extends Entity> void serialize(List<Pojo> entities, String filePath);

    <Pojo extends Entity> File serialize(List<Pojo> entities);
}
