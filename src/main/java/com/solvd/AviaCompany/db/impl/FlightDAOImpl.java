package com.solvd.AviaCompany.db.impl;

import com.solvd.AviaCompany.db.dao.IFlightDAO;
import com.solvd.AviaCompany.hierarchy.City;
import com.solvd.AviaCompany.hierarchy.Flight;
import com.solvd.AviaCompany.utils.connection.JDBCConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.solvd.AviaCompany.db.tablecolumns.FlightColumn.*;

public class FlightDAOImpl extends JDBCConnectionManager implements IFlightDAO {

    private static final Logger logger = LogManager.getLogger(FlightDAOImpl.class);

    private static final String GET_ALL_FLIGHTS = "SELECT * FROM Flights";
    private static final String INSERT_FLIGHT = "INSERT INTO Flights(dep_city_id," +
            "dest_city_id, cost, distance) VALUES(?,?,?,?)";

    @Override
    public boolean create(Flight entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(INSERT_FLIGHT);
            preparedStatement.setInt(1, entity.getDeparture().getId());
            preparedStatement.setInt(2, entity.getDestination().getId());
            preparedStatement.setInt(3, entity.getCost());
            preparedStatement.setInt(4, entity.getDistance());
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
    public List<Flight> read() {
        Connection connection = null;
        Statement statement = null;
        List<Flight> flightList = new ArrayList<>();
        try {
            connection = getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL_FLIGHTS);
            while (resultSet.next()) {
                City departure = new CityDAOImpl().read(resultSet.getInt(DEPARTURE.getColumn()));
                City destination = new CityDAOImpl().read(resultSet.getInt(DESTINATION.getColumn()));

                Flight flight = new Flight();
                flight.setId(resultSet.getInt(ID.getColumn()));
                flight.setDeparture(departure);
                flight.setDestination(destination);
                flight.setCost(resultSet.getInt(COST.getColumn()));
                flight.setDistance(resultSet.getInt(DISTANCE.getColumn()));
                flightList.add(flight);
            }
        } catch (SQLException e) {
            logger.warn("Wrong statement / Invalid field");
        } finally {
            close(statement);
            close(connection);
        }
        return flightList;
    }

    @Override
    public Flight update(Flight entity) {
        return entity;
    }

    @Override
    public Flight read(Integer id) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean delete(Flight entity) {
        return false;
    }
}
