package com.solvd.AviaCompany.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {
    private String pathToProp;
    private Properties prop;
    private static final Logger logger = LogManager.getLogger(PropertiesManager.class);
    private String defaultPath = "/src/main/resources/dbcreds.properties";

    public PropertiesManager() {
        pathToProp = System.getProperty("user.dir") + defaultPath;
        loadData();
    }

    private void loadData() {
        prop = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(pathToProp)){
            prop.load(fileInputStream);
        } catch (IOException e) {
            logger.warn("Couldn't find a property file");
        }
    }

    public String getProperty(String propertyName) {
        return prop.getProperty(propertyName);
    }
}
