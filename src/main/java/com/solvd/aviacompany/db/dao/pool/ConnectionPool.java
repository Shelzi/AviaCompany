package com.solvd.aviacompany.db.dao.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static ConnectionPool instance = null;
    private static final Logger logger = LogManager.getLogger();
    private final BlockingQueue<ProxyConnection> idleConnections;
    private static final int POOL_SIZE = Integer.parseInt(ConnectionCreator.getPoolSize());
    private static final int MAX_CONNECTION_ERROR_NUMBER = 4;

    public static ConnectionPool getInstance() {
        if (instance == null)
            instance = new ConnectionPool();
        return instance;
    }

    private ConnectionPool() {
        int errorCounter = 0;
        idleConnections = new ArrayBlockingQueue<>(POOL_SIZE);

        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                Connection connection = ConnectionCreator.createConnection();
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                idleConnections.add(proxyConnection);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Connection hasn't been created" + e);
                errorCounter++;
            }
        }
        if (errorCounter >= MAX_CONNECTION_ERROR_NUMBER) {
            logger.log(Level.FATAL, errorCounter + " connections haven't been created");
            throw new RuntimeException(errorCounter + " connections haven't been created");
        }
    }

    public void releaseConnection(ProxyConnection proxyConnection) {
        idleConnections.offer(proxyConnection);
    }

    public Connection takeConnection() {
        ProxyConnection connection;
        try {
            connection = idleConnections.take();
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "Сan't take connection: " + e);
            throw new RuntimeException(e);
        }
        return connection;
    }

    public void init() {
    }

    public void destroy() {
        for (ProxyConnection proxyConnection : idleConnections) {
            try {
                proxyConnection.finallyClose();
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Сan't close connection: " + e);
                throw new RuntimeException(e);
            }
            try {
                while (DriverManager.getDrivers().hasMoreElements()) {
                    Driver driver = DriverManager.getDrivers().nextElement();
                    DriverManager.deregisterDriver(driver);
                }
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Сan't deregister driver: " + e);
                throw new RuntimeException(e);
            }
        }
    }
}