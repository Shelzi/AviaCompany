package com.solvd.AviaCompany.db.impl;

import com.solvd.AviaCompany.db.dao.ITicketDAO;
import com.solvd.AviaCompany.hierarchy.City;
import com.solvd.AviaCompany.hierarchy.Flight;
import com.solvd.AviaCompany.hierarchy.Passenger;
import com.solvd.AviaCompany.hierarchy.Ticket;
import com.solvd.AviaCompany.utils.connection.JDBCConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.solvd.AviaCompany.db.tablecolumns.TicketColumn.*;

public class TicketDAOImpl extends JDBCConnectionManager implements ITicketDAO {

    private static final Logger LOGGER = LogManager.getLogger(TicketDAOImpl.class);
    private static final String INSERT_TICKET = "INSERT INTO Tickets(passengers_id, flights_id) VALUES(?, ?)";
    private static final String GET_ALL_TICKETS = "SELECT * FROM Tickets";

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
                Passenger passenger = new PassengerDAOImpl().read(resultSet.getInt(PASSENGER.getColumn()));
                Flight flight = new FlightDAOImpl().read(resultSet.getInt(FLIGHT.getColumn()));

                ticket.setPassenger(passenger);
                ticket.setFlight(flight);
                ticketList.add(ticket);
            }
        } catch (SQLException e) {
            LOGGER.warn("Wrong statement / Invalid field");
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
