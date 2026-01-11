package pl.softra.www.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    // Blok statyczny - uruchamia się tylko raz przy pierwszym użyciu klasy
    static {
        String path = "src/test/resources/configuration.properties";
        try (FileInputStream input = new FileInputStream(path)) {
            properties = new Properties();
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load configuration.properties file!", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}