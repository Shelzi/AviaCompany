package com.solvd.aviacompany.db.dao.impl;

import com.solvd.aviacompany.db.dao.ITicketDao;

import com.solvd.aviacompany.db.dao.constant.SqlQuery;
import com.solvd.aviacompany.db.dao.mapper.BaseMapper;
import com.solvd.aviacompany.db.dao.mapper.impl.TicketMapper;
import com.solvd.aviacompany.db.dao.pool.ConnectionPool;
import com.solvd.aviacompany.hierarchy.Ticket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class TicketDaoImpl implements ITicketDao {
    private static final Logger logger = LogManager.getLogger();
    private static final ConnectionPool pool = ConnectionPool.getInstance();
    private static final BaseMapper<Ticket> ticketMapper = new TicketMapper();

    @Override
    public boolean create(Ticket ticket) {
        try (Connection c = pool.takeConnection();
             PreparedStatement preparedStatement = c.prepareStatement(SqlQuery.SQL_INSERT_TICKET)) {
            preparedStatement.setInt(1, ticket.getPassenger().getId());
            preparedStatement.setInt(2, ticket.getFlight().getId());
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
    public List<Ticket> read() {
        List<Ticket> ticketList = new ArrayList<>();
        try (Connection c = pool.takeConnection();
             PreparedStatement preparedStatement = c.prepareStatement(SqlQuery.SQL_GET_ALL_TICKETS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ticketList.add(ticketMapper.map(resultSet));
            }
        } catch (SQLException e) {
            logger.warn("Wrong statement / Invalid field");
        }
        return ticketList;
    }

    @Override
    public boolean update(Ticket ticket) {
        return false;
    }

    @Override
    public Optional<Ticket> read(int id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(Ticket ticket) {
        return false;
    }
}
