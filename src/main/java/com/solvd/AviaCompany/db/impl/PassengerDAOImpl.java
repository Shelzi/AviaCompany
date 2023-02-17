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

    private static final Logger logger = LogManager.getLogger(PassengerDAOImpl.class);
    private static final String GET_PASSENGER_ID = "SELECT * FROM Passengers WHERE id = ?";
    private static final String INSERT_PASSENGER = "INSERT INTO Passengers(first_name, last_name) VALUES(?,?)";
    private static final String GET_ALL_PASSENGERS = "SELECT * FROM Passengers";
    private static final String UPDATE_PASSENGER = "UPDATE Passengers SET first_name=?, last_name=? WHERE id=?";
    private static final String GET_PASSENGER_BY_FNAME_LNAME = "SELECT * FROM Passengers " +
            "WHERE first_name = ? AND last_name = ?";

    @Override
    public boolean create(Passenger entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(INSERT_PASSENGER);
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            int rowAffected = preparedStatement.executeUpdate();
            if (rowAffected == 0) {
                logger.warn("No rows were inserted");
                return false;
            }
        } catch (SQLException e) {
            logger.warn("Wrong statement  / Invalid field");
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
                passenger.setFirstName(resultSet.getString(FIRST_NAME.getColumn()));
                passenger.setLastName(resultSet.getString(LAST_NAME.getColumn()));
                passengerList.add(passenger);
            }
        } catch (SQLException e) {
            logger.warn("Wrong statement / Invalid field");
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
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setInt(3, entity.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                logger.warn("No rows were inserted");
                return null;
            }
        } catch (SQLException e) {
            logger.warn("Wrong statement  / Invalid field");
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
                passenger.setFirstName(resultSet.getString(FIRST_NAME.getColumn()));
                passenger.setLastName(resultSet.getString(LAST_NAME.getColumn()));
                return passenger;
            }
        } catch (SQLException e) {
            logger.warn("Wrong statement  / Invalid field");
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

    @Override
    public Passenger getPassengerByFirstAndLastName(String firstName, String lastName) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(GET_PASSENGER_BY_FNAME_LNAME);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Passenger passenger = new Passenger();
                passenger.setId(resultSet.getInt(ID.getColumn()));
                passenger.setFname(resultSet.getString(FIRST_NAME.getColumn()));
                passenger.setLname(resultSet.getString(LAST_NAME.getColumn()));
                return passenger;
            }
        } catch (SQLException e) {
            logger.warn("Wrong statement  / Invalid field");
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return null;
    }
}
