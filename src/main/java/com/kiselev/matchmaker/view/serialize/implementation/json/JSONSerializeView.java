package com.kiselev.matchmaker.view.serialize.implementation.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kiselev.matchmaker.api.model.Entity;
import com.kiselev.matchmaker.view.serialize.SerializeView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

public class JSONSerializeView implements SerializeView {

    private static final String PATH = "export\\json\\";
    private static final String EXTENSION = ".json";

    @Override
    public <Pojo extends Entity> void serialize(List<Pojo> entities, String filePath) {
        try {
            writeJSONToFile(entities, filePath);
        } catch (IOException firstException) {
            try {
                writeJSONToNewFile(entities);
            } catch (IOException secondException) {
                secondException.printStackTrace();
            }
        }
    }

    @Override
    public <Pojo extends Entity> File serialize(List<Pojo> entities) {
        try {
            return writeJSONToNewFile(entities);
        } catch (IOException secondException) {
            secondException.printStackTrace();
        }
        return null;
    }

    private <Pojo extends Entity> File writeJSONToFile(List<Pojo> entities, String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(filePath);
        objectMapper.writeValue(file, entities);
        return file;
    }

    private <Pojo extends Entity> File writeJSONToNewFile(List<Pojo> entities) throws IOException {
        Path path = Paths.get(PATH);
        if (Files.notExists(path)) {
            Files.createDirectories(path);
        }

        return writeJSONToFile(entities, PATH + UUID.randomUUID().toString() + EXTENSION);
    }
}
