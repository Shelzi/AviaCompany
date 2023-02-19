package com.solvd.aviacompany.db.dao.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionCreator {
    private static final Properties properties = new Properties();
    private static final String DATABASE_URL;
    private static final String DATABASE_PROPERTY_FILE_PATH = "src/main/resources/dbcreds.properties";
    private static final String DATABASE_DRIVER_FIELD = "db.driver";
    private static final String DATABASE_URL_FIELD = "db.url";
    private static final String DATABASE_POOL_SIZE_FIELD = "pool.size";
    private static final String POOL_SIZE;

    private static final Logger logger = LogManager.getLogger();

    static {
        try {
            FileReader reader = new FileReader(DATABASE_PROPERTY_FILE_PATH);
            properties.load(reader);
            String driverName = (String) properties.get(DATABASE_DRIVER_FIELD);
            Class.forName(driverName);
        } catch (ClassNotFoundException | IOException e) {
            logger.log(Level.ERROR, "Couldn't load properties: " + e);
        }
        DATABASE_URL = properties.getProperty(DATABASE_URL_FIELD);
        POOL_SIZE = properties.getProperty(DATABASE_POOL_SIZE_FIELD);
    }

    private ConnectionCreator() {
    }

    public static String getPoolSize() {
        return POOL_SIZE;
    }

    static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, properties);
    }
}