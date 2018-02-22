package com.kiselev.matchmaker.view.serialize.implementation.xml;

import com.kiselev.matchmaker.api.model.Entity;
import com.kiselev.matchmaker.view.serialize.SerializeView;
import com.kiselev.matchmaker.view.serialize.implementation.xml.model.EntitiesList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

public class XMLSerializeView implements SerializeView {

    private static final String PATH = "export\\xml\\";
    private static final String EXTENSION = ".xml";

    @Override
    public <Pojo extends Entity> void serialize(List<Pojo> entities, String filePath) {
        try {
            writeXMLToFile(entities, filePath);
        } catch (JAXBException firstException) {

            try {
                Path path = Paths.get(PATH);
                if (Files.notExists(path)) {
                    Files.createDirectories(path);
                }

                writeXMLToFile(entities, PATH + UUID.randomUUID().toString() + EXTENSION);
            } catch (IOException | JAXBException secondException) {
                secondException.printStackTrace();
            }
        }
    }

    private <Pojo extends Entity> void writeXMLToFile(List<Pojo> entities, String filePath) throws JAXBException {

        EntitiesList<Pojo> entitiesList = new EntitiesList<>(entities);

        JAXBContext jaxbContext = JAXBContext.newInstance(entitiesList.getClass());
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        // output pretty printed
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        jaxbMarshaller.marshal(entitiesList, new File(filePath));
    }
}
