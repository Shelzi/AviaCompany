package com.solvd.AviaCompany.utils.connection;

import com.solvd.AviaCompany.utils.PropertiesManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCConnectionManager {

    private static final Logger LOGGER = LogManager.getLogger(JDBCConnectionManager.class);

    PropertiesManager propertiesManager;

    public Connection getConnection() {
        try {
            propertiesManager = new PropertiesManager();
            Class.forName(propertiesManager.getProperty("DRIVER_NAME"));

            return DriverManager.getConnection(
                    propertiesManager.getProperty("DB_URL"),
                    propertiesManager.getProperty("USER"),
                    propertiesManager.getProperty("PASSWORD"));
        } catch (ClassNotFoundException e) {
            LOGGER.warn("Couldn't find a driver");
        } catch (SQLException e) {
            LOGGER.warn("Wrong credentials");
        }
        return null;
    }

    public void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                LOGGER.warn("Couldn't close a connection");
            }
        }
    }

    public void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                LOGGER.warn("Couldn't end the statement");
            }
        }
    }
}
