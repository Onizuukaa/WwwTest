package pl.softra.www.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    // Blok statyczny - uruchamia się tylko raz przy pierwszym użyciu klasy
    static {
        try {
            // Wskazujemy ścieżkę do pliku
            String path = "src/test/resources/configuration.properties";

            // Otwieramy strumień danych
            FileInputStream input = new FileInputStream(path);

            // Inicjalizujemy obiekt Properties i ładujemy dane z pliku
            properties = new Properties();
            properties.load(input);

            input.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Nie udało się załadować pliku configuration.properties!");
        }
    }

    // Metoda, której będziesz używać w testach
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
