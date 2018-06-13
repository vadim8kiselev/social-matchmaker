package com.kiselev.matchmaker.view.serialize.implementation.excel;

import com.kiselev.matchmaker.api.model.Entity;
import com.kiselev.matchmaker.view.serialize.SerializeView;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

public class ExcelSerializeView implements SerializeView {

    private static final String PATH = "export/excel/";

    private static final String EXTENSION = ".xlsx";

    @Override
    public <Pojo extends Entity> void serialize(List<Pojo> entities, String filePath) {
        validateEntities(entities);

        try {
            writeXLSToFile(entities, filePath);
        } catch (IOException firstException) {
            try {
                writeXLSToNewFile(entities);
            } catch (IOException secondException) {
                secondException.printStackTrace();
            }
        }
    }

    @Override
    public <Pojo extends Entity> File serialize(List<Pojo> entities) {
        validateEntities(entities);

        try {
            return writeXLSToNewFile(entities);
        } catch (IOException secondException) {
            secondException.printStackTrace();
        }
        return null;
    }

    private <Pojo extends Entity> File writeXLSToFile(List<Pojo> entities, String filePath) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Sheet");

        if (CollectionUtils.isNotEmpty(entities)) {
            createHeader(sheet, entities.iterator().next());
            createBody(sheet, entities);
            updateColumns(sheet);
        }

        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            workbook.write(outputStream);
        }

        return new File(filePath);
    }

    private <Pojo extends Entity> File writeXLSToNewFile(List<Pojo> entities) throws IOException {
        Path path = Paths.get(PATH);
        if (Files.notExists(path)) {
            Files.createDirectories(path);
        }

        return writeXLSToFile(entities, PATH + UUID.randomUUID().toString() + EXTENSION);
    }

    private void updateColumns(XSSFSheet sheet) {
        for (int column = 0; column < 256; column++) {
            sheet.autoSizeColumn(column);
        }
    }

    private <Pojo extends Entity> void createHeader(XSSFSheet sheet, Pojo entity) {
        XSSFRow header = sheet.createRow(0);

        Class<? extends Entity> clazz = entity.getClass();
        Field[] fields = clazz.getDeclaredFields();

        int columnCounter = 0;
        for (Field field : fields) {
            XSSFCell headerCell = header.createCell(columnCounter++);

            headerCell.setCellValue(field.getName());
        }
    }

    private <Pojo extends Entity> void createBody(XSSFSheet sheet, List<Pojo> entities) {
        int rowCounter = sheet.getLastRowNum() + 1;
        for (Pojo entity : entities) {
            XSSFRow row = sheet.createRow(rowCounter++);

            int columnCounter = 0;
            for (Field field : entity.getClass().getDeclaredFields()) {
                XSSFCell cell = row.createCell(columnCounter++);

                field.setAccessible(true);
                try {
                    cell.setCellValue(field.get(entity).toString());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private <Pojo extends Entity> void validateEntities(List<Pojo> entities) {
        Class<? extends Entity> firstPojoClass = entities.iterator().next().getClass();

        boolean allMatch = entities.stream()
                .map(Object::getClass)
                .allMatch(clazz -> clazz.equals(firstPojoClass));

        if (!allMatch) {
            throw new RuntimeException("All entities should be same type for excel serialization!");
        }
    }
}