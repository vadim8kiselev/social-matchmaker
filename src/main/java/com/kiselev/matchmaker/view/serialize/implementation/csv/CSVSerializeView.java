package com.kiselev.matchmaker.view.serialize.implementation.csv;

import com.google.common.collect.Lists;
import com.kiselev.matchmaker.api.model.Entity;
import com.kiselev.matchmaker.view.serialize.SerializeView;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CSVSerializeView implements SerializeView {

    private static final String PATH = "export\\csv\\";
    private static final String EXTENSION = ".csv";

    @Override
    public <Pojo extends Entity> void serialize(List<Pojo> entities, String filePath) {
        validateEntities(entities);

        try {
            writeCSVToFile(entities, filePath);
        } catch (IOException firstException) {
            try {
                writeCSVToNewFile(entities);
            } catch (IOException secondException) {
                secondException.printStackTrace();
            }
        }
    }

    @Override
    public <Pojo extends Entity> File serialize(List<Pojo> entities) {
        validateEntities(entities);

        try {
            return writeCSVToNewFile(entities);
        } catch (IOException secondException) {
            secondException.printStackTrace();
        }
        return null;
    }

    private <Pojo extends Entity> File writeCSVToFile(List<Pojo> entities, String filePath) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath));
             CSVPrinter csvPrinter = new CSVPrinter(writer, composeHeader(entities))) {

            List<List<String>> records = entities.stream()
                    .map(this::composeRecord)
                    .collect(Collectors.toList());

            for (List<String> record : records) {
                csvPrinter.printRecord(record);
            }

            csvPrinter.flush();
        }
        return new File(filePath);
    }

    private <Pojo extends Entity> File writeCSVToNewFile(List<Pojo> entities) throws IOException {
        Path path = Paths.get(PATH);
        if (Files.notExists(path)) {
            Files.createDirectories(path);
        }

        return writeCSVToFile(entities, PATH + UUID.randomUUID().toString() + EXTENSION);
    }


    private <Pojo extends Entity> CSVFormat composeHeader(List<Pojo> entities) {
        CSVFormat csvFormat = CSVFormat.DEFAULT;

        if (CollectionUtils.isNotEmpty(entities)) {

            Class<? extends Entity> clazz = entities.iterator().next().getClass();
            String[] fieldsNames = Arrays.stream(clazz.getDeclaredFields())
                    .map(Field::getName)
                    .collect(Collectors.toList())
                    .toArray(new String[]{});

            csvFormat = csvFormat.withHeader(fieldsNames);
        }

        return csvFormat;
    }

    private <Pojo extends Entity> List<String> composeRecord(Pojo entity) {
        List<String> record = Lists.newArrayList();

        for (Field field : entity.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                record.add(field.get(entity).toString());
            } catch (IllegalAccessException | NullPointerException e) {
                e.printStackTrace();
            }
        }

        return record;
    }

    private <Pojo extends Entity> void validateEntities(List<Pojo> entities) {
        Class<? extends Entity> firstPojoClass = entities.iterator().next().getClass();

        boolean allMatch = entities.stream()
                .map(Object::getClass)
                .allMatch(clazz -> clazz.equals(firstPojoClass));

        if (!allMatch) {
            throw new RuntimeException("All entities should be same type for csv serialization!");
        }
    }
}
