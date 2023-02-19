package com.solvd.aviacompany.db.dao.mapper.impl;

import com.solvd.aviacompany.db.dao.mapper.BaseMapper;
import com.solvd.aviacompany.db.tablecolumns.Passengers;
import com.solvd.aviacompany.hierarchy.Passenger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public class PassengerMapper implements BaseMapper<Passenger> {
    @Override
    public Passenger map(ResultSet resultSet) throws SQLException {
        return new Passenger(
                resultSet.getInt("passengers.id"),
                resultSet.getString("passengers.first_name"),
                resultSet.getString("passengers.last_name"));
    }
}
