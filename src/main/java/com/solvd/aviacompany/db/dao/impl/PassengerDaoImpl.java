package com.solvd.aviacompany.db.dao.impl;

import com.solvd.aviacompany.db.dao.IPassengerDao;
import com.solvd.aviacompany.db.dao.constant.SqlQuery;
import com.solvd.aviacompany.db.dao.mapper.BaseMapper;
import com.solvd.aviacompany.db.dao.mapper.impl.PassengerMapper;
import com.solvd.aviacompany.db.dao.pool.ConnectionPool;
import com.solvd.aviacompany.hierarchy.Passenger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PassengerDaoImpl implements IPassengerDao {

    private static final Logger logger = LogManager.getLogger();
    private static final ConnectionPool pool = ConnectionPool.getInstance();
    private static final BaseMapper<Passenger> passengerMapper = new PassengerMapper();

    @Override
    public boolean create(Passenger passenger) {
        try (Connection c = pool.takeConnection();
             PreparedStatement preparedStatement = c.prepareStatement(SqlQuery.SQL_INSERT_PASSENGER)) {
            preparedStatement.setString(1, passenger.getFirstName());
            preparedStatement.setString(2, passenger.getLastName());
            int rowAffected = preparedStatement.executeUpdate();
            if (rowAffected == 0) {
                logger.warn("No rows were inserted");
                return false;
            }
        } catch (SQLException e) {
            logger.warn("Wrong statement  / Invalid field");
        }
        return true;
    }

    @Override
    public List<Passenger> read() {
        List<Passenger> passengerList = new ArrayList<>();
        try (Connection c = pool.takeConnection();
             PreparedStatement preparedStatement = c.prepareStatement(SqlQuery.SQL_GET_ALL_PASSENGERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                passengerList.add(passengerMapper.map(resultSet));
            }
        } catch (SQLException e) {
            logger.warn("Wrong statement / Invalid field");
        }
        return passengerList;
    }

    @Override
    public Optional<Passenger> read(int id) {
        Optional<Passenger> passengerOptional = Optional.empty();
        try (Connection c = pool.takeConnection();
             PreparedStatement preparedStatement = c.prepareStatement(SqlQuery.SQL_GET_PASSENGER_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                passengerOptional = Optional.of(passengerMapper.map(resultSet));
            }
        } catch (SQLException e) {
            logger.warn("Wrong statement  / Invalid field");
        }
        return passengerOptional;
    }

    @Override
    public boolean update(Passenger passenger) {
        try (Connection c = pool.takeConnection();
             PreparedStatement preparedStatement = c.prepareStatement(SqlQuery.SQL_UPDATE_PASSENGER)) {
            preparedStatement.setString(1, passenger.getFirstName());
            preparedStatement.setString(2, passenger.getLastName());
            preparedStatement.setInt(3, passenger.getId());
            return (preparedStatement.executeUpdate() == 1);
        } catch (SQLException e) {
            logger.warn("Wrong statement  / Invalid field");
            return false;
        }
    }

    @Override
    public Optional<Passenger> getPassengerByFirstAndLastName(String firstName, String lastName) {
        Optional<Passenger> passengerOptional = Optional.empty();
        try (Connection c = pool.takeConnection();
             PreparedStatement preparedStatement =
                     c.prepareStatement(SqlQuery.SQL_GET_PASSENGER_BY_FIRST_NAME_AND_LAST_NAME)) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                passengerOptional = Optional.of(passengerMapper.map(resultSet));
            }
        } catch (SQLException e) {
            logger.warn("Wrong statement  / Invalid field");
        }
        return passengerOptional;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(Passenger passenger) {
        return false;
    }

    public int getAutoIncrement(){
        int id = 1;
        try (Connection c = pool.takeConnection();
             PreparedStatement preparedStatement = c.prepareStatement(SqlQuery.SQL_GET_AUTO_INCREMENT)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.warn("Wrong statement / Invalid field" + e.getMessage());
        }
        return id;
    }
}
