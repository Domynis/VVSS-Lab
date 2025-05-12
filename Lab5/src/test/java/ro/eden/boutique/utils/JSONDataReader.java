package ro.eden.boutique.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ro.eden.boutique.models.RegistrationData;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class JSONDataReader {

    public static List<RegistrationData> readRegistrationData(String fileName) {
        ObjectMapper objectMapper = new ObjectMapper();

        try (InputStream inputStream = JSONDataReader.class.getClassLoader().getResourceAsStream(fileName)) {
            return objectMapper.readValue(inputStream, new TypeReference<List<RegistrationData>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON file: " + fileName, e);
        }
    }
}