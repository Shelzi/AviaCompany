package com.solvd.aviacompany.db.impl;

import com.solvd.aviacompany.db.dao.IFlightDAO;
import com.solvd.aviacompany.db.tablecolumns.CityColumn;
import com.solvd.aviacompany.db.tablecolumns.CountryColumn;
import com.solvd.aviacompany.db.tablecolumns.FlightColumn;
import com.solvd.aviacompany.hierarchy.City;
import com.solvd.aviacompany.hierarchy.Country;
import com.solvd.aviacompany.hierarchy.Flight;
import com.solvd.aviacompany.utils.connection.JDBCConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.solvd.aviacompany.db.tablecolumns.TicketColumn.FLIGHT;

public class FlightDAOImpl extends JDBCConnectionManager implements IFlightDAO {

    private static final Logger logger = LogManager.getLogger(FlightDAOImpl.class);

    private static final String GET_ALL_FLIGHTS = "SELECT f.id as flights_id, f.cost, f.distance, " +
            "dep_city.name as dep_name, dep_city.id as dep_id, " +
            "dest_city.name as dest_name, dest_city.id as dest_id, " +
            "dep_co.id as dep_country_id, dep_co.name as dep_country_name, " +
            "dest_co.id as dest_country_id, dest_co.name as dest_country_name " +
            "FROM Flights f LEFT JOIN city dep_city ON f.dep_city_id = dep_city.id " +
            "LEFT JOIN city dest_city ON f.dest_city_id = dest_city.id " +
            "LEFT JOIN country dep_co ON dep_city.country_id = dep_co.id " +
            "LEFT JOIN country dest_co ON dest_city.country_id = dest_co.id";
    private static final String INSERT_FLIGHT = "INSERT INTO Flights(dep_city_id," +
            "dest_city_id, cost, distance) VALUES(?,?,?,?)";

    private static final String GET_FLIGHT_ID = GET_ALL_FLIGHTS + " WHERE f.id = ?";

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
                Flight flight = new Flight();
                City departureCity = new City();
                City destinationCity = new City();
                Country departureCountry = new Country();
                Country destinationCountry = new Country();

                flight.setId(resultSet.getInt(FLIGHT.getColumn()));
                flight.setDistance(resultSet.getInt(FlightColumn.DISTANCE.getColumn()));
                flight.setCost(resultSet.getInt(FlightColumn.COST.getColumn()));

                departureCity.setId(resultSet.getInt("dep_" + CityColumn.ID.getColumn()));
                departureCity.setName(resultSet.getString("dep_" + CityColumn.NAME.getColumn()));

                destinationCity.setId(resultSet.getInt("dest_" + CityColumn.ID.getColumn()));
                destinationCity.setName(resultSet.getString("dest_" + CityColumn.NAME.getColumn()));

                departureCountry.setId(resultSet.getInt("dep_country_" + CountryColumn.ID.getColumn()));
                departureCountry.setName(resultSet.getString("dep_country_" + CountryColumn.NAME.getColumn()));

                destinationCountry.setId(resultSet.getInt("dest_country_" + CountryColumn.ID.getColumn()));
                destinationCountry.setName(resultSet.getString("dest_country_" + CountryColumn.NAME.getColumn()));

                departureCity.setCountry(departureCountry);
                destinationCity.setCountry(destinationCountry);

                flight.setDeparture(departureCity);
                flight.setDestination(destinationCity);
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
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(GET_FLIGHT_ID);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Flight flight = new Flight();
                City departureCity = new City();
                City destinationCity = new City();
                Country departureCountry = new Country();
                Country destinationCountry = new Country();

                flight.setId(resultSet.getInt(FLIGHT.getColumn()));
                flight.setDistance(resultSet.getInt(FlightColumn.DISTANCE.getColumn()));
                flight.setCost(resultSet.getInt(FlightColumn.COST.getColumn()));

                departureCity.setId(resultSet.getInt("dep_" + CityColumn.ID.getColumn()));
                departureCity.setName(resultSet.getString("dep_" + CityColumn.NAME.getColumn()));

                destinationCity.setId(resultSet.getInt("dest_" + CityColumn.ID.getColumn()));
                destinationCity.setName(resultSet.getString("dest_" + CityColumn.NAME.getColumn()));

                departureCountry.setId(resultSet.getInt("dep_country_" + CountryColumn.ID.getColumn()));
                departureCountry.setName(resultSet.getString("dep_country_" + CountryColumn.NAME.getColumn()));

                destinationCountry.setId(resultSet.getInt("dest_country_" + CountryColumn.ID.getColumn()));
                destinationCountry.setName(resultSet.getString("dest_country_" + CountryColumn.NAME.getColumn()));

                departureCity.setCountry(departureCountry);
                destinationCity.setCountry(destinationCountry);

                flight.setDeparture(departureCity);
                flight.setDestination(destinationCity);
                return flight;
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
    public boolean delete(Flight entity) {
        return false;
    }
}
