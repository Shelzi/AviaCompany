package com.solvd.aviacompany.db.impl;

import com.solvd.aviacompany.db.dao.ITicketDAO;
import com.solvd.aviacompany.db.tablecolumns.CityColumn;
import com.solvd.aviacompany.db.tablecolumns.CountryColumn;
import com.solvd.aviacompany.db.tablecolumns.FlightColumn;
import com.solvd.aviacompany.db.tablecolumns.PassengerColumn;
import com.solvd.aviacompany.hierarchy.*;
import com.solvd.aviacompany.utils.connection.JDBCConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.solvd.aviacompany.db.tablecolumns.TicketColumn.*;

public class TicketDAOImpl extends JDBCConnectionManager implements ITicketDAO {

    private static final Logger logger = LogManager.getLogger(TicketDAOImpl.class);
    private static final String INSERT_TICKET = "INSERT INTO Tickets(passengers_id, flights_id) VALUES(?, ?)";
    private static final String GET_ALL_TICKETS = "SELECT p.id as passengers_id, p.first_name, p.last_name, " +
            "f.id as flights_id, f.cost, f.distance, dep_city.name as dep_name, dep_city.id as dep_id, " +
            "dest_city.name as dest_name, dest_city.id as dest_id, " +
            "dep_co.id as dep_country_id, dep_co.name as dep_country_name,  " +
            "dest_co.id as dest_country_id, dest_co.name as dest_country_name " +
            "FROM tickets t LEFT JOIN passengers p ON t.passengers_id = p.id " +
            "LEFT JOIN flights f ON t.flights_id = f.id " +
            "LEFT JOIN city dep_city ON f.dep_city_id = dep_city.id " +
            "LEFT JOIN city dest_city ON f.dest_city_id = dest_city.id " +
            "LEFT JOIN country dep_co ON dep_city.country_id = dep_co.id " +
            "LEFT JOIN country dest_co ON dest_city.country_id = dest_co.id";

    @Override
    public boolean create(Ticket entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(INSERT_TICKET);
            preparedStatement.setInt(1, entity.getPassenger().getId());
            preparedStatement.setInt(2, entity.getFlight().getId());
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
    public List<Ticket> read() {
        Connection connection = null;
        Statement statement = null;
        List<Ticket> ticketList = new ArrayList<>();
        try {
            connection = getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL_TICKETS);
            while (resultSet.next()) {
                Ticket ticket = new Ticket();
                Passenger passenger = new Passenger();
                Flight flight = new Flight();
                City departureCity = new City();
                City destinationCity = new City();
                Country departureCountry = new Country();
                Country destinationCountry = new Country();

                passenger.setId(resultSet.getInt(PASSENGER.getColumn()));
                passenger.setFirstName(resultSet.getString(PassengerColumn.FIRST_NAME.getColumn()));
                passenger.setLastName(resultSet.getString(PassengerColumn.LAST_NAME.getColumn()));

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

                ticket.setPassenger(passenger);
                ticket.setFlight(flight);
                ticketList.add(ticket);
            }
        } catch (SQLException e) {
            logger.warn("Wrong statement / Invalid field");
        } finally {
            close(statement);
            close(connection);
        }
        return ticketList;
    }

    @Override
    public Ticket update(Ticket entity) {
        return null;
    }

    @Override
    public Ticket read(Integer id) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean delete(Ticket entity) {
        return false;
    }
}
