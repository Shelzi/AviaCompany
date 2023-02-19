package com.solvd.aviacompany.utils.connection;

import com.solvd.aviacompany.utils.PropertiesManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCConnectionManager {
    private static final Logger logger = LogManager.getLogger(JDBCConnectionManager.class);
    private static final PropertiesManager propertiesManager = new PropertiesManager();

    public Connection getConnection() {
        try {
            Class.forName(propertiesManager.getProperty("DRIVER_NAME"));

            return DriverManager.getConnection(
                    propertiesManager.getProperty("DB_URL"),
                    propertiesManager.getProperty("USER"),
                    propertiesManager.getProperty("PASSWORD"));
        } catch (ClassNotFoundException e) {
            logger.warn("Couldn't find a driver");
        } catch (SQLException e) {
            logger.warn("Wrong credentials");
        }
        return null;
    }

    public void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                logger.warn("Couldn't close a connection");
            }
        }
    }

    public void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                logger.warn("Couldn't end the statement");
            }
        }
    }
}
