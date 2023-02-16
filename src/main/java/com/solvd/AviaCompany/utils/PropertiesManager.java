package com.solvd.AviaCompany.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {
    private String pathToProp;
    private Properties prop;
    private static final Logger LOGGER = LogManager.getLogger(PropertiesManager.class);
    private String defaultPath = "/src/main/resources/dbcreds.properties";

    public PropertiesManager() {
        pathToProp = System.getProperty("user.dir") + defaultPath;
    }

    public PropertiesManager(String pathToProp) {
        this.pathToProp = System.getProperty("user.dir") + pathToProp;
    }

    private void loadData() {
        prop = new Properties();
        try {
            prop.load(new FileInputStream(pathToProp));
        } catch (IOException e) {
            LOGGER.warn("Couldn't find a property file");
        }
    }

    public String getProperty(String propertyName) {
        loadData();
        return prop.getProperty(propertyName);
    }
}
