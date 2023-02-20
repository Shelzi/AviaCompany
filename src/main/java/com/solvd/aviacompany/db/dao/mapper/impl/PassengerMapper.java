package com.solvd.aviacompany.db.dao.mapper.impl;

import com.solvd.aviacompany.db.dao.mapper.BaseMapper;
import com.solvd.aviacompany.hierarchy.Passenger;

import java.sql.ResultSet;
import java.sql.SQLException;


public class PassengerMapper implements BaseMapper<Passenger> {
    @Override
    public Passenger map(ResultSet resultSet) throws SQLException {
        return Passenger.builder()
                .id(resultSet.getInt("passengers.id"))
                .firstName(resultSet.getString("passengers.first_name"))
                .lastName(resultSet.getString("passengers.last_name"))
                .build();
    }
}
