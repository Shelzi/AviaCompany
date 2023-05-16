package com.solvd.aviacompany.db.dao.mapper.impl;

import com.solvd.aviacompany.db.dao.mapper.BaseMapper;
import com.solvd.aviacompany.hierarchy.Flight;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FlightMapper implements BaseMapper<Flight> {
    @Override
    public Flight map(ResultSet resultSet) throws SQLException {
        return Flight.builder()
                .id(resultSet.getInt("flights.id"))
                .cost(resultSet.getInt("flights.cost"))
                .distance(resultSet.getInt("flights.distance"))
                .departure(new CityMapper().map(resultSet))
                .destination(new CityMapper().map(resultSet))
                .build();
    }
}
