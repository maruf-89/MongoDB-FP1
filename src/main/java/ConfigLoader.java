package mogodbDemo.atlas;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigLoader.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {

            if (input == null) {
                throw new RuntimeException(
                        "Kunde inte hitta config.properties – " +
                                "kopiera config.properties.example till config.properties " +
                                "och fyll i dina uppgifter.");
            }

            properties.load(input);

        } catch (IOException e) {
            throw new RuntimeException("Fel vid inläsning av config.properties", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}