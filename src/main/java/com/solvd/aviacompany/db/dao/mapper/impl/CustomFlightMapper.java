package com.solvd.aviacompany.db.dao.mapper.impl;

import com.solvd.aviacompany.db.dao.impl.CityDaoImpl;
import com.solvd.aviacompany.db.dao.mapper.BaseMapper;
import com.solvd.aviacompany.hierarchy.Flight;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomFlightMapper implements BaseMapper<Flight> {
    @Override
    public Flight map(ResultSet resultSet) throws SQLException {
        return Flight.builder()
                .destination(new CityDaoImpl().read(resultSet.getInt("flights.dest_city_id")).get())
                .departure(new CityDaoImpl().read(resultSet.getInt("flights.dep_city_id")).get())
                .id(resultSet.getInt("flights.id"))
                .cost(resultSet.getInt("flights.cost"))
                .distance(resultSet.getInt("flights.distance"))
                .build();
    }
}
