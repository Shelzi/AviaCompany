package com.solvd.aviacompany.db.dao.impl;

import com.solvd.aviacompany.db.dao.IFlightDao;
import com.solvd.aviacompany.db.dao.constant.SqlQuery;
import com.solvd.aviacompany.db.dao.mapper.BaseMapper;
import com.solvd.aviacompany.db.dao.mapper.impl.FlightMapper;
import com.solvd.aviacompany.db.dao.pool.ConnectionPool;
import com.solvd.aviacompany.hierarchy.Flight;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class FlightDaoImpl implements IFlightDao {
    private static final Logger logger = LogManager.getLogger();
    private static final ConnectionPool pool = ConnectionPool.getInstance();
    private static final BaseMapper<Flight> flightMapper = new FlightMapper();

    @Override
    public boolean create(Flight flight) {
        try (Connection c = pool.takeConnection();
             PreparedStatement preparedStatement = c.prepareStatement(SqlQuery.SQL_INSERT_FLIGHT)) {
            preparedStatement.setInt(1, flight.getDeparture().getId());
            preparedStatement.setInt(2, flight.getDestination().getId());
            preparedStatement.setInt(3, flight.getCost());
            preparedStatement.setInt(4, flight.getDistance());
            int rowAffected = preparedStatement.executeUpdate();
            if (rowAffected == 0) {
                logger.warn("No rows were inserted");
                return false;
            }
        } catch (SQLException e) {
            logger.warn("Wrong statement / Invalid field");
        }
        return true;
    }

    @Override
    public List<Flight> read() {
        List<Flight> flightList = new ArrayList<>();
        try (Connection c = pool.takeConnection();
             PreparedStatement preparedStatement = c.prepareStatement(SqlQuery.SQL_GET_ALL_FLIGHTS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Flight flight = Flight.builder()
                        .destination(new CityDaoImpl().read(resultSet.getInt("flights.dest_city_id")).get())
                        .departure(new CityDaoImpl().read(resultSet.getInt("flights.dep_city_id")).get())
                        .id(resultSet.getInt("flights.id"))
                        .cost(resultSet.getInt("flights.cost"))
                        .distance(resultSet.getInt("flights.distance"))
                        .build();
                flightList.add(flight);
                //TODO: rewrite to normal mapper, this is bad realisation of Dao read() method. Maybe do building in service
                //flightList.add(flightMapper.map(resultSet));
            }
        } catch (SQLException e) {
            logger.warn("Wrong statement / Invalid field");
        }
        return flightList;
    }

    @Override
    public Optional<Flight> read(int id) {
        Optional<Flight> flightOptional = Optional.empty();
        try (Connection c = pool.takeConnection();
             PreparedStatement preparedStatement = c.prepareStatement(SqlQuery.SQL_GET_FLIGHT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                flightOptional = Optional.of(flightMapper.map(resultSet));
            }
        } catch (SQLException e) {
            logger.warn("Wrong statement / Invalid field");
        }
        return flightOptional;
    }

    @Override
    public boolean update(Flight flight) {
        return false;

    }
    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(Flight flight) {
        return false;
    }
}
