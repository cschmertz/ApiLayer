package dataProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigReader {

    private static final Logger LOGGER = Logger.getLogger(ConfigReader.class.getName());
    private static ConfigReader instance;
    private Properties properties;

    private ConfigReader() {
        try (BufferedReader reader = new BufferedReader(new FileReader("configs/configuration.properties"))) {
            properties = new Properties();
            properties.load(reader);
        } catch (IOException e) {
            String fileName = "configs/configuration.properties";
            LOGGER.log(Level.SEVERE, "Error reading configuration file: " + fileName + " - " + e.getMessage(), e);
        }
    }

    public static ConfigReader getInstance() {
        if (instance == null) {
            instance = new ConfigReader();
        }
        return instance;
    }

    public String getBaseUrl() {
        return properties.getProperty("base_Url");
    }

    public String getEmail(){
        return properties.getProperty("email");
    }

    public String getWrongEmail() {return properties.getProperty("wrongEmail");}

    public String getPassword(){
        return properties.getProperty("password");
    }

    public String getName(){
        return properties.getProperty("name");
    }

    public String getJob(){
        return properties.getProperty("job");
    }

}
