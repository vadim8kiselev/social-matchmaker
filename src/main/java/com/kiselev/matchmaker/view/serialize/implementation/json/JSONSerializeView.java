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
                Path path = Paths.get(PATH);
                if (Files.notExists(path)) {
                    Files.createDirectories(path);
                }

                writeJSONToFile(entities, PATH + UUID.randomUUID().toString() + EXTENSION);
            } catch (IOException secondException) {
                secondException.printStackTrace();
            }
        }
    }

    private <Pojo extends Entity> void writeJSONToFile(List<Pojo> entities, String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(filePath), entities);
    }
}
