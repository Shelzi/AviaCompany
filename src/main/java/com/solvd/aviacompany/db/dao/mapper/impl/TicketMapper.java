package com.solvd.aviacompany.db.dao.mapper.impl;

import com.solvd.aviacompany.db.dao.mapper.BaseMapper;
import com.solvd.aviacompany.hierarchy.Ticket;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketMapper implements BaseMapper<Ticket> {
    @Override
    public Ticket map(ResultSet resultSet) throws SQLException {
        return Ticket.builder()
                .id(resultSet.getInt("tickets.id"))
                .flight(new CustomFlightMapper().map(resultSet))
                .passenger(new PassengerMapper().map(resultSet))
                .build();
    }
}
