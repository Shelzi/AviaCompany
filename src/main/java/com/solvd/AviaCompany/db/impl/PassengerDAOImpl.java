package com.solvd.AviaCompany.db.impl;

import com.solvd.AviaCompany.db.dao.IPassengerDAO;
import com.solvd.AviaCompany.hierarchy.Passenger;
import com.solvd.AviaCompany.utils.connection.JDBCConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.solvd.AviaCompany.db.tablecolumns.PassengerColumn.*;

public class PassengerDAOImpl extends JDBCConnectionManager implements IPassengerDAO {

    private static final Logger LOGGER = LogManager.getLogger(PassengerDAOImpl.class);
    private static final String GET_PASSENGER_ID = "SELECT * FROM Passengers WHERE id = ?";
    private static final String INSERT_PASSENGER = "INSERT INTO Passengers(first_name, last_name) VALUES(?,?)";
    private static final String GET_ALL_PASSENGERS = "SELECT * FROM Passengers";
    private static final String UPDATE_PASSENGER = "UPDATE Passengers SET first_name=?, last_name=? WHERE id=?";

    @Override
    public boolean create(Passenger entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(INSERT_PASSENGER);
            preparedStatement.setString(1, entity.getFname());
            preparedStatement.setString(2, entity.getLname());
            int rowAffected = preparedStatement.executeUpdate();
            if (rowAffected == 0) {
                LOGGER.warn("No rows were inserted");
                return false;
            }
        } catch (SQLException e) {
            LOGGER.warn("Wrong statement  / Invalid field");
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return true;
    }

    @Override
    public List<Passenger> read() {
        Connection connection = null;
        Statement statement = null;
        List<Passenger> passengerList = new ArrayList<>();
        try {
            connection = getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL_PASSENGERS);
            while (resultSet.next()) {
                Passenger passenger = new Passenger();
                passenger.setId(resultSet.getInt(ID.getColumn()));
                passenger.setFname(resultSet.getString(FIRST_NAME.getColumn()));
                passenger.setLname(resultSet.getString(LAST_NAME.getColumn()));
                passengerList.add(passenger);
            }
        } catch (SQLException e) {
            LOGGER.warn("Wrong statement / Invalid field");
        } finally {
            close(statement);
            close(connection);
        }
        return passengerList;
    }

    @Override
    public Passenger update(Passenger entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_PASSENGER);
            preparedStatement.setString(1, entity.getFname());
            preparedStatement.setString(2, entity.getLname());
            preparedStatement.setInt(3, entity.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0){
                LOGGER.warn("No rows were inserted");
                return entity;
            }
        } catch (SQLException e) {
            LOGGER.warn("Wrong statement  / Invalid field");
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return entity;
    }

    @Override
    public Passenger read(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(GET_PASSENGER_ID);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Passenger passenger = new Passenger();
                passenger.setId(resultSet.getInt(ID.getColumn()));
                passenger.setFname(resultSet.getString(FIRST_NAME.getColumn()));
                passenger.setLname(resultSet.getString(LAST_NAME.getColumn()));
                return passenger;
            }
        } catch (SQLException e) {
            LOGGER.warn("Wrong statement  / Invalid field");
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean delete(Passenger entity) {
        return false;
    }
}
